package com.beknumonov.star_shop.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.star_shop.activities.ProductDetailsActivity;
import com.beknumonov.star_shop.base.BaseListAdapter;
import com.beknumonov.star_shop.base.BaseViewHolder;
import com.beknumonov.star_shop.databinding.ItemProductBinding;
import com.beknumonov.star_shop.model.Product;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductListAdapter extends BaseListAdapter {

    private ArrayList<Product> productArrayList;

    public ProductListAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Product", "Product id=" + productArrayList.get(position).id);

                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailsActivity.class);
                intent.putExtra("product_id", productArrayList.get(position).id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }


    class ProductViewHolder extends BaseViewHolder<ItemProductBinding> {

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            Product product = productArrayList.get(position);

            binding.productName.setText(product.getTitle());
            binding.productBrand.setText(product.getBrand());
            binding.productPriceCurrent.setText(product.getPriceCurrentAsString());
            binding.productPriceOriginal.setText(product.getPriceOriginalAsString());

            if (product.getImage() != null)
                Glide.with(binding.productImage).load(product.getImage().getImage()).into(binding.productImage);
        }
    }
}
