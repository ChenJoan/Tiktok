package com.bytedance.tiktok.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bytedance.tiktok.adapter.CommentAdapter;
import com.bytedance.tiktok.bean.CommentBean;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.DialogCommentBinding;
import java.util.ArrayList;

/**
 * 评论弹框
 */
public class CommentDialog extends BaseBottomSheetDialog {
    protected DialogCommentBinding binding;

    private CommentAdapter commentAdapter;
    private final ArrayList<CommentBean> dataList = new ArrayList<>();
    private final int[] likeArray = new int[]{4919, 334, 121, 423, 221, 23};
    private final String[] commentArray = new String[]{"我就说左脚踩右脚可以上天你们还不信！", "全是评论点赞，没人关注吗", "哈哈哈哈", "像谁，没看出来", "你这西安话真好听"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogCommentBinding.inflate(LayoutInflater.from(getContext()), container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), dataList);
        binding.recyclerview.setAdapter(commentAdapter);

        loadData();
    }

    private void loadData() {
        for (VideoBean.UserBean userBean : DataCreate.userList) {
            CommentBean commentBean = new CommentBean();
            commentBean.setUserBean(userBean);
            commentBean.setContent(commentArray[(int) (Math.random() * commentArray.length)]);
            commentBean.setLikeCount(likeArray[(int) (Math.random() * likeArray.length)]);
            dataList.add(commentBean);
        }
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 600;
    }
}
