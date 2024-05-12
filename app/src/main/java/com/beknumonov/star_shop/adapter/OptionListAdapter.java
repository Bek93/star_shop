package com.beknumonov.star_shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.star_shop.R;
import com.beknumonov.star_shop.base.BaseListAdapter;
import com.beknumonov.star_shop.base.BaseViewHolder;
import com.beknumonov.star_shop.databinding.ItemOptionBinding;
import com.beknumonov.star_shop.dialog.OptionDialog;
import com.beknumonov.star_shop.model.ColorOption;
import com.beknumonov.star_shop.model.SizeOption;

import java.util.ArrayList;

public class OptionListAdapter extends BaseListAdapter {

    private ArrayList<ColorOption> colorOptionArrayList;
    private ArrayList<SizeOption> sizeOptionArrayList;


    private ColorOption selectedColorOption;
    private SizeOption selectedSizeOption;

    public void setSelectedColorOption(ColorOption selectedColorOption) {
        this.selectedColorOption = selectedColorOption;
    }

    public void setSelectedSizeOption(SizeOption selectedSizeOption) {
        this.selectedSizeOption = selectedSizeOption;
    }

    private OptionDialog.OptionItemSelectListener listener;

    public void setListener(OptionDialog.OptionItemSelectListener listener) {
        this.listener = listener;
    }

    public OptionListAdapter(ArrayList<ColorOption> colorOptionArrayList, ArrayList<SizeOption> sizeOptionArrayList) {
        this.colorOptionArrayList = colorOptionArrayList;
        this.sizeOptionArrayList = sizeOptionArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOptionBinding binding = ItemOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.itemView.findViewById(R.id.tvOptionTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorOptionArrayList != null && sizeOptionArrayList == null) {
                    // color is selected
                    ColorOption colorOption = colorOptionArrayList.get(position);
                    if (listener != null)
                        listener.onColorItemSelected(colorOption);

                } else if (colorOptionArrayList == null && sizeOptionArrayList != null) {
                    // size is selected

                    SizeOption sizeOption = sizeOptionArrayList.get(position);
                    if (listener != null)
                        listener.onSizeItemSelected(sizeOption);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (colorOptionArrayList != null && sizeOptionArrayList == null) {
            return colorOptionArrayList.size();
        } else if (colorOptionArrayList == null && sizeOptionArrayList != null) {
            return sizeOptionArrayList.size();
        }
        return 0;
    }

    public class OptionViewHolder extends BaseViewHolder<ItemOptionBinding> {

        public OptionViewHolder(@NonNull ItemOptionBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            if (colorOptionArrayList != null && sizeOptionArrayList == null) {

                ColorOption colorOption = colorOptionArrayList.get(position);
                binding.tvOptionTitle.setText(colorOption.getTitle());
                if (selectedColorOption != null && selectedColorOption.getId() == colorOption.getId()) {
                    binding.ivOptionCheck.setVisibility(View.VISIBLE);
                } else {
                    binding.ivOptionCheck.setVisibility(View.GONE);
                }

            } else if (colorOptionArrayList == null && sizeOptionArrayList != null) {
                SizeOption sizeOption = sizeOptionArrayList.get(position);
                binding.tvOptionTitle.setText(sizeOption.getTitle());

                if (selectedSizeOption != null && selectedSizeOption.getId() == sizeOption.getId()) {
                    binding.ivOptionCheck.setVisibility(View.VISIBLE);
                } else {
                    binding.ivOptionCheck.setVisibility(View.GONE);
                }
            }

        }
    }
}
