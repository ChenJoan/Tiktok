package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import com.bytedance.tiktok.adapter.WorkAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentWorkBinding;

/**
 * 个人作品fragment
 */
public class WorkFragment extends BaseFragment<FragmentWorkBinding> {
    @Override
    protected void init() {
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        WorkAdapter workAdapter = new WorkAdapter(getActivity(), DataCreate.datas);
        binding.recyclerview.setAdapter(workAdapter);
    }

}
