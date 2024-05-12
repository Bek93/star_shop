package com.beknumonov.star_shop.activities;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.star_shop.adapter.ProductListAdapter;
import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.ActivityProductListBinding;
import com.beknumonov.star_shop.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ProductListActivity extends BaseActivity<ActivityProductListBinding> {

    private ProductListAdapter adapter;
    private ArrayList<Product> productArrayList;

    @Override
    protected ActivityProductListBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityProductListBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productArrayList = new ArrayList<>();
        adapter = new ProductListAdapter(productArrayList);
        binding.productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.productsRecyclerView.setAdapter(adapter);
        int subProduct = getIntent().getIntExtra("subProduct", 0);

        if (subProduct > 0) {
            loadProductList(subProduct);
        }

        //binding.productsRecyclerView.invalidate();
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    private void loadProductList(int subProduct) {

        Call<ArrayList<Product>> call = mainApi.getProductsBySubProduct(subProduct);

        call.enqueue(new RequestCallback<ArrayList<Product>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Product>> call, Throwable t) {

            }
        });


    }
}
