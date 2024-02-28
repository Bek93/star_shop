package com.beknumonov.star_shop.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
}
