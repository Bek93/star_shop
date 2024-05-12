package com.beknumonov.star_shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.star_shop.base.BaseListAdapter;
import com.beknumonov.star_shop.base.BaseViewHolder;
import com.beknumonov.star_shop.databinding.ItemCartBinding;
import com.beknumonov.star_shop.databinding.ItemOrderedProductBinding;
import com.beknumonov.star_shop.model.Cart;
import com.beknumonov.star_shop.model.OrderProduct;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderedProductListAdapter extends BaseListAdapter {

    private ArrayList<OrderProduct> orderProductArrayList;

    public OrderedProductListAdapter(ArrayList<OrderProduct> orderProductArrayList) {
        this.orderProductArrayList = orderProductArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderedProductBinding binding = ItemOrderedProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderProductViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }


    public class OrderProductViewHolder extends BaseViewHolder<ItemOrderedProductBinding> {

        public OrderProductViewHolder(@NonNull ItemOrderedProductBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            OrderProduct orderProduct = orderProductArrayList.get(position);

            binding.tvProductTitle.setText(orderProduct.getProduct().getTitle());
            if (orderProduct.getProduct().getImage() != null)
                Glide.with(binding.ivProductImage).load(orderProduct.getProduct().getImage().getImage()).into(binding.ivProductImage);

            binding.tvColor.setText(orderProduct.getOption().getColorOption().getTitle());
            binding.tvSize.setText(orderProduct.getOption().getSizeOption().getTitle());
            binding.tvPrice.setText(orderProduct.getProduct().getPriceCurrentAsString());
            binding.tvQuantity.setText(String.valueOf(orderProduct.getQuantity()));

            // quantity * product.current_price.

            int total_price = orderProduct.getQuantity() * orderProduct.getProduct().getPriceCurrent();
            DecimalFormat format = new DecimalFormat("###,###,###₩");
            binding.tvTotalPrice.setText(format.format(total_price));

        }
    }

    public interface CartSelectorListener {
        void onSelected(boolean isSelected);
    }
}
