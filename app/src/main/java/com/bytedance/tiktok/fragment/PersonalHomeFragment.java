package com.bytedance.tiktok.fragment;

import android.content.Intent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.FocusActivity;
import com.bytedance.tiktok.activity.ShowImageActivity;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.CurUserBean;
import com.bytedance.tiktok.bean.MainPageChangeEvent;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.FragmentPersonalHomeBinding;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.utils.RxBus;
import com.google.android.material.appbar.AppBarLayout;
import java.util.ArrayList;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 个人主页fragment
 */
public class PersonalHomeFragment extends BaseFragment<FragmentPersonalHomeBinding> {
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private VideoBean.UserBean curUserBean;
    private Subscription subscription;

    @Override
    protected void init() {

        //解决toolbar左边距问题
        binding.toolbar.setContentInsetsAbsolute(0, 0);

        setAppbarLayoutPercent();

        binding.ivReturn.setOnClickListener(v -> {
            RxBus.getDefault().post(new MainPageChangeEvent(0));
        });
        binding.personalHomeHeader.ivHead.setOnClickListener(v->{
            transitionAnim(binding.personalHomeHeader.ivHead, curUserBean.getHead());
        });
        binding.ivBg.setOnClickListener(v->{

        });
        binding.personalHomeHeader.llFocus.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), FocusActivity.class));
        });
        binding.personalHomeHeader.llFans.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), FocusActivity.class));
        });

        setUserInfo();
    }

    /**
     * 过渡动画跳转页面
     *
     * @param view
     */
    public void transitionAnim(View view, int res) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.trans));
        Intent intent = new Intent(getActivity(), ShowImageActivity.class);
        intent.putExtra("res", res);
        ActivityCompat.startActivity(getActivity(), intent, compat.toBundle());
    }

    public void setUserInfo() {
        subscription = RxBus.getDefault().toObservable(CurUserBean.class).subscribe((Action1<CurUserBean>) curUserBean -> {

            coordinatorLayoutBackTop();

            this.curUserBean = curUserBean.getUserBean();

            binding.ivBg.setImageResource(curUserBean.getUserBean().getHead());
            binding.personalHomeHeader.ivHead.setImageResource(curUserBean.getUserBean().getHead());
            binding.personalHomeHeader.tvNickname.setText(curUserBean.getUserBean().getNickName());
            binding.personalHomeHeader.tvSign.setText(curUserBean.getUserBean().getSign());
            binding.tvTitle.setText(curUserBean.getUserBean().getNickName());

            String subCount = NumUtils.numberFilter(curUserBean.getUserBean().getSubCount());
            String focusCount = NumUtils.numberFilter(curUserBean.getUserBean().getFocusCount());
            String fansCount = NumUtils.numberFilter(curUserBean.getUserBean().getFansCount());

            //获赞 关注 粉丝
            binding.personalHomeHeader.tvGetlikeCount.setText(subCount);
            binding.personalHomeHeader.tvFocusCount.setText(focusCount);
            binding.personalHomeHeader.tvFansCount.setText(fansCount);

            //关注状态
            if (curUserBean.getUserBean().isFocused()) {
                binding.personalHomeHeader.tvAddfocus.setText("已关注");
                binding.personalHomeHeader.tvAddfocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                binding.personalHomeHeader.tvAddfocus.setText("关注");
                binding.personalHomeHeader.tvAddfocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            setTabLayout();
        });
    }

    private void setTabLayout() {
        String[] titles = new String[]{"作品 " + curUserBean.getWorkCount(), "动态 " + curUserBean.getDynamicCount(), "喜欢 " + curUserBean.getLikeCount()};

        fragments.clear();
        for (String title : titles) {
            fragments.add(new WorkFragment());
            binding.tablayout.addTab(binding.tablayout.newTab().setText(title));
        }

        CommPagerAdapter pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, titles);
        binding.viewpager.setAdapter(pagerAdapter);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    /**
     * 根据滚动比例渐变view
     */
    private void setAppbarLayoutPercent() {
        binding.appbarlayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());  //滑动比例

            if (percent > 0.8) {
                binding.tvTitle.setVisibility(View.VISIBLE);
                binding.tvFocus.setVisibility(View.VISIBLE);

                float alpha = 1 - (1 - percent) * 5;  //渐变变换
                binding.tvTitle.setAlpha(alpha);
                binding.tvFocus.setAlpha(alpha);
            } else {
                binding.tvTitle.setVisibility(View.GONE);
                binding.tvFocus.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 自动回顶部
     */
    private void coordinatorLayoutBackTop() {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) binding.appbarlayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
