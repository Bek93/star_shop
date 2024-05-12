package com.beknumonov.star_shop.activities;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.star_shop.adapter.OrderedProductListAdapter;
import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.databinding.ActivityOrderDetailsBinding;
import com.beknumonov.star_shop.model.Order;
import com.beknumonov.star_shop.model.OrderProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderDetailsActivity extends BaseActivity<ActivityOrderDetailsBinding> {
    @Override
    protected ActivityOrderDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityOrderDetailsBinding.inflate(inflater);
    }

    private Order order;
    private OrderedProductListAdapter orderedProductListAdapter;
    private ArrayList<OrderProduct> orderProductArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order = (Order) getIntent().getSerializableExtra("order");
        renderUI();
    }

    private void renderUI() {


        if (order != null) {
            orderProductArrayList.addAll(order.getOrderProducts());
            orderedProductListAdapter = new OrderedProductListAdapter(orderProductArrayList);

            binding.productsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.productsRecyclerView.setAdapter(orderedProductListAdapter);

            binding.tvOrderNumber.setText(order.getOrderNumber());
            binding.tvOrderStatus.setText(order.getStatus());
            binding.tvOrderPayment.setText(order.getPayment());

            DecimalFormat format = new DecimalFormat("###,###,###₩");
            binding.tvTotalPrice.setText(format.format(order.getTotalCurrentPrice()));
            binding.tvUsername.setText(order.getName());
            binding.tvPhoneNumber.setText(order.getPhone());
            binding.tvAddress.setText(order.getAddress());
        }

    }
}
