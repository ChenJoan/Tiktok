package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.activity.PlayListActivity;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ItemWorkBinding;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.viewHolder.WorkViewHolder;
import java.util.List;

/**
 * 作品adapter
 */
public class WorkAdapter extends BaseRvAdapter<VideoBean, WorkViewHolder> {

    public WorkAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(WorkViewHolder holder, VideoBean videoBean, int position) {
        holder.getBinding().ivCover.setImageResource(videoBean.getCoverRes());
        holder.getBinding().tvLikecount.setText(NumUtils.numberFilter(videoBean.getLikeCount()));

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkBinding itemWorkBinding = ItemWorkBinding.inflate(LayoutInflater.from(context),parent,false);
        return new WorkViewHolder(itemWorkBinding);
    }

}
