package com.bytedance.tiktok.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.PrivateLetterAdapter;
import com.bytedance.tiktok.adapter.ShareAdapter;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.bean.ShareBean;
import com.bytedance.tiktok.databinding.DialogShareBinding;
import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-25
 * description 分享弹框
 */
public class ShareDialog extends BaseBottomSheetDialog {
    protected DialogShareBinding binding;
    private PrivateLetterAdapter privateLetterAdapter;
    private ShareAdapter shareAdapter;
    private ArrayList<ShareBean> shareBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogShareBinding.inflate(LayoutInflater.from(getContext()), container, false);
        init();
        return binding.getRoot();
    }

    private void init() {

        binding.rvPrivateLetter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        privateLetterAdapter = new PrivateLetterAdapter(getContext(), DataCreate.userList);
        binding.rvPrivateLetter.setAdapter(privateLetterAdapter);

        binding.rvShare.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        shareAdapter = new ShareAdapter(getContext(), shareBeans);
        binding.rvShare.setAdapter(shareAdapter);

        setShareDatas();

    }

    private void setShareDatas() {
        shareBeans.add(new ShareBean(R.string.icon_friends, "朋友圈", R.color.color_wechat_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_wechat, "微信", R.color.color_wechat_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_qq, "QQ", R.color.color_qq_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_qq_space, "QQ空间", R.color.color_qqzone_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_weibo, "微博", R.color.color_weibo_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_comment, "私信好友", R.color.color_FF0041));

        shareAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return dp2px(getContext(), 355);
    }

}
