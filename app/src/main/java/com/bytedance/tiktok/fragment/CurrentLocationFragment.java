package com.bytedance.tiktok.fragment;

import android.os.CountDownTimer;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.GridVideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentCurrentLocationBinding;

/**
 * create by libo
 * create on 2020-05-19
 * description 附近的人fragment
 */
public class CurrentLocationFragment extends BaseFragment<FragmentCurrentLocationBinding> {
    private GridVideoAdapter adapter;

    @Override
    protected void init() {
        new DataCreate().initData();
        
        binding.recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new GridVideoAdapter(getActivity(), DataCreate.datas);
        binding.recyclerview.setAdapter(adapter);

        binding.refreshlayout.setColorSchemeResources(R.color.color_link);
        binding.refreshlayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                binding.refreshlayout.setRefreshing(false);
            }
        }.start());
    }

}
