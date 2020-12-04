package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.CommentBean;
import com.bytedance.tiktok.databinding.ItemCommentBinding;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.viewHolder.ItemCommentViewHolder;

import java.util.List;

/**
 * commentAdapter 评论Adapter
 */
public class CommentAdapter extends BaseRvAdapter<CommentBean, ItemCommentViewHolder> {

    public CommentAdapter(Context context, List<CommentBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(ItemCommentViewHolder holder, CommentBean commentBean, int position) {
        holder.getBinding().ivHead.setImageResource(commentBean.getUserBean().getHead());
        holder.getBinding().tvNickname.setText(commentBean.getUserBean().getNickName());
        holder.getBinding().tvContent.setText(commentBean.getContent());
        holder.getBinding().tvLikecount.setText(NumUtils.numberFilter(commentBean.getLikeCount()));
    }

    @NonNull
    @Override
    public ItemCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding viewBinding = ItemCommentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ItemCommentViewHolder(viewBinding);
    }
}
