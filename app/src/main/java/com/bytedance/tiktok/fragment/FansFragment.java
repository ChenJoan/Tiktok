package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.bytedance.tiktok.adapter.FansAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentFansBinding;

/**
 * 粉丝/关注人fragment
 */
public class FansFragment extends BaseFragment<FragmentFansBinding> {

    @Override
    protected void init() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FansAdapter fansAdapter = new FansAdapter(getContext(), DataCreate.userList);
        binding.recyclerview.setAdapter(fansAdapter);
    }

}
