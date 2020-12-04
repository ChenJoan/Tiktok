package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ItemFansBinding;
import com.bytedance.tiktok.viewHolder.FansViewHolder;

import java.util.List;

/**
 * 粉丝页面adapter
 */
public class FansAdapter extends BaseRvAdapter<VideoBean.UserBean, FansViewHolder> {

    public FansAdapter(Context context, List<VideoBean.UserBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(FansViewHolder holder, VideoBean.UserBean userBean, int position) {
        holder.getBinding().ivHead.setImageResource(userBean.getHead());
        holder.getBinding().tvNickname.setText(userBean.getNickName());
        holder.getBinding().tvFocus.setText(userBean.isFocused() ? "已关注" : "关注");

        holder.getBinding().tvFocus.setOnClickListener(v -> {
            if (!userBean.isFocused()) {
                holder.getBinding().tvFocus.setText("已关注");
                holder.getBinding().tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                holder.getBinding().tvFocus.setText("关注");
                holder.getBinding().tvFocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            userBean.setFocused(!userBean.isFocused());
        });
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFansBinding itemFansBinding = ItemFansBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FansViewHolder(itemFansBinding);
    }
}
