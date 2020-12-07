package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ViewControllerBinding;
import com.bytedance.tiktok.utils.AutoLinkHerfManager;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.utils.OnVideoControllerListener;
/**
 *
 */
public class ControllerView extends RelativeLayout {
    protected ViewControllerBinding binding;

    private OnVideoControllerListener listener;
    private VideoBean videoData;

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        binding = ViewControllerBinding.inflate(LayoutInflater.from(context), null , false);
        init();
    }

    private void init() {
        binding.ivHead.setOnClickListener(v->{
            listener.onHeadClick();
        });
        binding.ivComment.setOnClickListener(v->{
            listener.onCommentClick();
        });
        binding.ivShare.setOnClickListener(v->{
            listener.onShareClick();
        });
        binding.rlLike.setOnClickListener(v->{
            listener.onLikeClick();
        });
        binding.ivFocus.setOnClickListener(v->{
            if (!videoData.isFocused()) {
                videoData.setLiked(true);
                binding.ivFocus.setVisibility(GONE);
            }
        });

        setRotateAnim();
    }

    public void setVideoData(VideoBean videoData) {
        this.videoData = videoData;

        binding.ivHead.setImageResource(videoData.getUserBean().getHead());
        binding.tvNickname.setText("@" + videoData.getUserBean().getNickName());
        AutoLinkHerfManager.setContent(videoData.getContent(), binding.tvContent);
        binding.ivHeadAnim.setImageResource(videoData.getUserBean().getHead());
        binding.tvLikecount.setText(NumUtils.numberFilter(videoData.getLikeCount()));
        binding.tvCommentcount.setText(NumUtils.numberFilter(videoData.getCommentCount()));
        binding.tvSharecount.setText(NumUtils.numberFilter(videoData.getShareCount()));

        binding.lottieAnim.setAnimation("like.json");

        //点赞状态
        if (videoData.isLiked()) {
            binding.ivLike.setTextColor(getResources().getColor(R.color.color_FF0041));
        } else {
            binding.ivLike.setTextColor(getResources().getColor(R.color.white));
        }

        //关注状态
        if (videoData.isFocused()) {
            binding.ivFocus.setVisibility(GONE);
        } else {
            binding.ivFocus.setVisibility(VISIBLE);
        }
    }

    public void setListener(OnVideoControllerListener listener) {
        this.listener = listener;
    }

    /**
     * 点赞动作
     */
    public void like() {
        if (!videoData.isLiked()) {
            //点赞
            binding.lottieAnim.setVisibility(VISIBLE);
            binding.lottieAnim.playAnimation();
            binding.ivLike.setTextColor(getResources().getColor(R.color.color_FF0041));
        } else {
            //取消点赞
            binding.lottieAnim.setVisibility(INVISIBLE);
            binding.ivLike.setTextColor(getResources().getColor(R.color.white));
        }

        videoData.setLiked(!videoData.isLiked());
    }

    /**
     * 循环旋转动画
     */
    private void setRotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(8000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        binding.rlRecord.startAnimation(rotateAnimation);
    }
}
