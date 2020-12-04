package com.bytedance.tiktok.base;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * Recycler ViewHolder基类
 */
public class BaseRvViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {

    protected T binding;

    public BaseRvViewHolder(@NonNull T viewBinding) {
        super(viewBinding.getRoot());
        this.binding = viewBinding;
    }

    public void setBinding(T binding) {
        this.binding = binding;
    }

    public T getBinding() {
        return this.binding;
    }


}
