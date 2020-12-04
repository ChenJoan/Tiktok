package com.bytedance.tiktok.activity;

import androidx.fragment.app.Fragment;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.databinding.ActivityFocusViewBindBinding;
import com.bytedance.tiktok.fragment.FansFragment;
import java.util.ArrayList;

/**
 * 粉丝关注人页面
 */
public class FocusActivity extends BaseActivity<ActivityFocusViewBindBinding> {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private String[] titles = new String[] {"关注 128", "粉丝 128", "推荐关注"};

    @Override
    protected void init() {
        for (String title : titles) {
            fragments.add(new FansFragment());
            binding.tablayout.addTab(binding.tablayout.newTab().setText(title));
        }

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, titles);
        binding.viewpager.setAdapter(pagerAdapter);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }
}
