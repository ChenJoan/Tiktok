package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ItemVideoBinding;
import com.bytedance.tiktok.viewHolder.VideoViewHolder;
import java.util.List;

/**
 * videoAdapter
 */
public class VideoAdapter extends BaseRvAdapter<VideoBean, VideoViewHolder> {

    public VideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(VideoViewHolder holder, VideoBean videoBean, int position) {
        holder.getBinding().controller.setVideoData(videoBean);

        holder.getBinding().ivCover.setImageResource(videoBean.getCoverRes());

        holder.getBinding().likeview.setOnLikeListener(() -> {
            if (!videoBean.isLiked()) {  //未点赞，会有点赞效果，否则无
                holder.getBinding().controller.like();
            }

        });
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding itemVideoBinding = ItemVideoBinding.inflate(LayoutInflater.from(context), parent, false);
        return new VideoViewHolder(itemVideoBinding);
    }
}
