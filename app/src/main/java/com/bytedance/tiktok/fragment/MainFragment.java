package com.bytedance.tiktok.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.PauseVideoEvent;
import com.bytedance.tiktok.databinding.FragmentMainBinding;
import com.bytedance.tiktok.utils.RxBus;
import java.util.ArrayList;
import java.util.Objects;
/**
 * 主页fragment
 */
public class MainFragment extends BaseFragment<FragmentMainBinding> {
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    /** 默认显示第一页推荐页 */
    public static int curPage = 1;

    @Override
    protected void init() {
        setFragments();

        setMainMenu();
    }

    private void setFragments() {
        CurrentLocationFragment currentLocationFragment = new CurrentLocationFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);

        binding.tabTitle.addTab(binding.tabTitle.newTab().setText("海淀"));
        binding.tabTitle.addTab(binding.tabTitle.newTab().setText("推荐"));

        CommPagerAdapter pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"海淀", "推荐"});
        binding.viewpager.setAdapter(pagerAdapter);
        binding.tabTitle.setupWithViewPager(binding.viewpager);

        Objects.requireNonNull(binding.tabTitle.getTabAt(1)).select();

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                curPage = position;

                if (position == 1) {
                    //继续播放
                    RxBus.getDefault().post(new PauseVideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    RxBus.getDefault().post(new PauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setMainMenu() {
        binding.tabMainmenu.addTab(binding.tabMainmenu.newTab().setText("首页"));
        binding.tabMainmenu.addTab(binding.tabMainmenu.newTab().setText("好友"));
        binding.tabMainmenu.addTab(binding.tabMainmenu.newTab().setText(""));
        binding.tabMainmenu.addTab(binding.tabMainmenu.newTab().setText("消息"));
        binding.tabMainmenu.addTab(binding.tabMainmenu.newTab().setText("我"));
    }

}
