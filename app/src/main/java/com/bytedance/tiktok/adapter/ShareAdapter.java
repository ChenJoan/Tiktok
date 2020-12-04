package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.ShareBean;
import com.bytedance.tiktok.databinding.ItemShareBinding;
import com.bytedance.tiktok.viewHolder.ShareViewHolder;

import java.util.List;

/**
 *
 */
public class ShareAdapter extends BaseRvAdapter<ShareBean, ShareViewHolder> {

    public ShareAdapter(Context context, List<ShareBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(ShareViewHolder holder, ShareBean shareBean, int position) {
        holder.getBinding().tvIcon.setText(shareBean.getIconRes());
        holder.getBinding().tvText.setText(shareBean.getText());
        holder.getBinding().viewBg.setBackgroundResource(shareBean.getBgRes());
    }

    @NonNull
    @Override
    public ShareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShareBinding itemShareBinding = ItemShareBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ShareViewHolder(itemShareBinding);
    }
}
