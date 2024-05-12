package com.beknumonov.star_shop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.star_shop.activities.OrderDetailsActivity;
import com.beknumonov.star_shop.adapter.CartListAdapter;
import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.FragmentCartBinding;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;
import com.beknumonov.star_shop.dialog.MyDialogs;
import com.beknumonov.star_shop.model.Cart;
import com.beknumonov.star_shop.model.Order;
import com.beknumonov.star_shop.model.OrderRequest;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class CartFragment extends BaseFragment<FragmentCartBinding> {

    private ArrayList<Cart> cartArrayList;

    private CartListAdapter adapter;

    @Override
    protected FragmentCartBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentCartBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartArrayList = new ArrayList<>();
        adapter = new CartListAdapter(cartArrayList);

    }

    @Override
    public void onStart() {
        super.onStart();
        loadMyCarts();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.cartRecyclerView.setAdapter(adapter);

        adapter.setCartSelectorListener(new CartListAdapter.CartSelectorListener() {
            @Override
            public void onSelected(boolean isSelected) {
                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice() {

        int total_price = 0;
        for (Cart cart : cartArrayList) {
            if (cart.isSelected()) {
                total_price += cart.getQuantity() * cart.getProduct().getPriceCurrent();
            }
        }
        DecimalFormat format = new DecimalFormat("###,###,###₩");
        binding.tvTotalPrice.setText(format.format(total_price));


        binding.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> cartIds = new ArrayList<>();
                for (Cart cart : cartArrayList) {
                    if (cart.isSelected()) {
                        cartIds.add(cart.getId());
                    }
                }

                if (cartIds.size() == 0) {
                    MyDialogs.showNoticeDialog(getChildFragmentManager(), "Please select cart first");
                    return;
                }

                // Create order request.

                OrderRequest orderRequest = new OrderRequest(cartIds);

                Call<Order> call = parent.mainApi.orderCarts(orderRequest);
                call.enqueue(new RequestCallback<Order>() {
                    @Override
                    protected void onResponseSuccess(Call<Order> call, Response<Order> response) {
                        Intent intent = new Intent(requireContext(), OrderDetailsActivity.class);
                        Order order = response.body();
                        intent.putExtra("order", order);
                        startActivity(intent);

                    }

                    @Override
                    protected void onResponseFailed(Call<Order> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        calculateTotalPrice();
    }

    private void loadMyCarts() {
        Call<ArrayList<Cart>> call = parent.mainApi.getMyCart();
        cartArrayList.clear();

        call.enqueue(new RequestCallback<ArrayList<Cart>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                cartArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Cart>> call, Throwable t) {

            }
        });
    }
}
