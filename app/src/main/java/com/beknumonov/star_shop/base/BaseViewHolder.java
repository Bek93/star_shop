package com.beknumonov.star_shop.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {

    protected VB binding;

    public BaseViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    protected abstract void onBind(int position);
}
