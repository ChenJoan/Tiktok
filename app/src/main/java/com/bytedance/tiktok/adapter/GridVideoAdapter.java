package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.activity.PlayListActivity;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ItemGridvideoBinding;
import com.bytedance.tiktok.viewHolder.GridVideoViewHolder;

import java.util.List;

/**
 * gridVideoAdapter
 */
public class GridVideoAdapter extends BaseRvAdapter<VideoBean, GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, VideoBean videoBean, int position) {
        holder.getBinding().ivCover.setBackgroundResource(videoBean.getCoverRes());
        holder.getBinding().tvContent.setText(videoBean.getContent());
        holder.getBinding().tvDistance.setText(videoBean.getDistance() + " km");
        holder.getBinding().ivHead.setImageResource(videoBean.getUserBean().getHead());

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public GridVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridvideoBinding itemGridvideoBinding = ItemGridvideoBinding.inflate(LayoutInflater.from(context), parent, false);
        return new GridVideoViewHolder(itemGridvideoBinding);
    }
}
