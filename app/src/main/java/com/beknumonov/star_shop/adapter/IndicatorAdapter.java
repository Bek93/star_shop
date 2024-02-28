package com.beknumonov.star_shop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.star_shop.base.BaseListAdapter;
import com.beknumonov.star_shop.base.BaseViewHolder;
import com.beknumonov.star_shop.databinding.ItemIndicatorBinding;

public class IndicatorAdapter extends BaseListAdapter {

    private int bannerListSize;
    private int selectedPosition = 0;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void setBannerListSize(int bannerListSize) {
        this.bannerListSize = bannerListSize;
    }

    public IndicatorAdapter(int bannerListSize) {
        this.bannerListSize = bannerListSize;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIndicatorBinding binding = ItemIndicatorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new IndicatorViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return bannerListSize;
    }

    class IndicatorViewHolder extends BaseViewHolder<ItemIndicatorBinding> {

        public IndicatorViewHolder(@NonNull ItemIndicatorBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            binding.dotImageView.setSelected(selectedPosition == position);
        }
    }
}
