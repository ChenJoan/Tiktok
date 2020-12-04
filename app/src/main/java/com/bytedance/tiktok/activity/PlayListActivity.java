package com.bytedance.tiktok.activity;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.databinding.ActivityPlayListBinding;
import com.bytedance.tiktok.fragment.RecommendFragment;

/**
 * 视频全屏播放页
 */
public class PlayListActivity extends BaseActivity<ActivityPlayListBinding> {
    public static int initPos;

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }
}
