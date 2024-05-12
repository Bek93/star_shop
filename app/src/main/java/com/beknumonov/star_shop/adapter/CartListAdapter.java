package com.beknumonov.star_shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.star_shop.base.BaseListAdapter;
import com.beknumonov.star_shop.base.BaseViewHolder;
import com.beknumonov.star_shop.databinding.ItemCartBinding;
import com.beknumonov.star_shop.model.Cart;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartListAdapter extends BaseListAdapter {

    private ArrayList<Cart> cartArrayList;
    private CartSelectorListener cartSelectorListener;

    public void setCartSelectorListener(CartSelectorListener cartSelectorListener) {
        this.cartSelectorListener = cartSelectorListener;
    }

    public CartListAdapter(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }


    public class CartViewHolder extends BaseViewHolder<ItemCartBinding> {

        public CartViewHolder(@NonNull ItemCartBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            Cart cart = cartArrayList.get(position);

            binding.tvProductTitle.setText(cart.getProduct().getTitle());
            if (cart.getProduct().getImage() != null)
                Glide.with(binding.ivProductImage).load(cart.getProduct().getImage().getImage()).into(binding.ivProductImage);

            binding.tvColor.setText(cart.getOption().getColorOption().getTitle());
            binding.tvSize.setText(cart.getOption().getSizeOption().getTitle());
            binding.tvPrice.setText(cart.getProduct().getPriceCurrentAsString());
            binding.tvQuantity.setText(String.valueOf(cart.getQuantity()));

            // quantity * product.current_price.

            int total_price = cart.getQuantity() * cart.getProduct().getPriceCurrent();
            DecimalFormat format = new DecimalFormat("###,###,###₩");
            binding.tvTotalPrice.setText(format.format(total_price));

            binding.ivSelectCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isSelected = binding.ivSelectCart.isSelected();
                    binding.ivSelectCart.setSelected(!isSelected);
                    cart.setSelected(!isSelected);
                    if (cartSelectorListener != null) {
                        cartSelectorListener.onSelected(!isSelected);
                    }

                }
            });

        }
    }

    public interface CartSelectorListener {
        void onSelected(boolean isSelected);
    }
}
