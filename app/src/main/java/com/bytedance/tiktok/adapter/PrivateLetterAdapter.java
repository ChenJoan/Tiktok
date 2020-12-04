package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.ItemPrivateLetterBinding;
import com.bytedance.tiktok.viewHolder.PrivateLetterViewHolder;

import java.util.List;

/**
 * create by libo
 * create on 2020-05-25
 * description
 */
public class PrivateLetterAdapter extends BaseRvAdapter<VideoBean.UserBean, PrivateLetterViewHolder> {

    public PrivateLetterAdapter(Context context, List<VideoBean.UserBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(PrivateLetterViewHolder holder, VideoBean.UserBean userBean, int position) {
        holder.getBinding().ivHead.setImageResource(userBean.getHead());
        holder.getBinding().tvNickname.setText(userBean.getNickName());
    }

    @NonNull
    @Override
    public PrivateLetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPrivateLetterBinding itemPrivateLetterBinding = ItemPrivateLetterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PrivateLetterViewHolder(itemPrivateLetterBinding);
    }
}
