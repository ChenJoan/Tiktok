package com.bytedance.tiktok.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.databinding.ActivitySplashBinding;

/**
 * App启动页面
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void init() {
        setFullScreen();

        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
