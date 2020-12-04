package com.bytedance.tiktok.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.tiktok.utils.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

/**
 * fragment 基类
 */
public abstract class BaseFragment<T extends ViewBinding> extends Fragment {
    protected T binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = getBinding(container);
        init();
        return binding.getRoot();
    }

    // 获取binding
    protected T getBinding(ViewGroup container){
        try {
            Type superClass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            Class<?> clazz = ClassUtils.getRawType(type);
            Method method = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            return (T) method.invoke(null, getLayoutInflater(), container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
