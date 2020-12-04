package com.bytedance.tiktok.activity;

import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.databinding.ActivityShowImageBinding;

/**
 * 查看照片activity
 */
public class ShowImageActivity extends BaseActivity<ActivityShowImageBinding> {

    @Override
    protected void init() {
        binding.ivHead.setOnClickListener(v -> finish());

        int headRes = getIntent().getIntExtra("res", 0);
        binding.ivHead.setImageResource(headRes);
    }
}
