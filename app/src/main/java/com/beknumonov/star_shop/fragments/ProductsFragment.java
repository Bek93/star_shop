package com.beknumonov.star_shop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.star_shop.activities.ProductListActivity;
import com.beknumonov.star_shop.adapter.CategoryListAdapter;
import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;
import com.beknumonov.star_shop.databinding.FragmentProductsBinding;
import com.beknumonov.star_shop.model.Category;
import com.beknumonov.star_shop.model.Classification;
import com.beknumonov.star_shop.model.SubProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ProductsFragment extends BaseFragment<FragmentProductsBinding> {

    private ArrayList<Classification> classificationArrayList;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<SubProduct> subProductArrayList;
    private CategoryListAdapter adapter;

    @Override
    protected FragmentProductsBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProductsBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classificationArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        subProductArrayList = new ArrayList<>();
        adapter = new CategoryListAdapter(classificationArrayList, categoryArrayList, subProductArrayList);

        adapter.setSelectListener(new CategoryListAdapter.CategorySelectListener() {
            @Override
            public void onCategorySelected(String type, int itemId) {

                if (type.equals("CLASSIFICATION")) {
                    binding.topPanel.setVisibility(View.VISIBLE);
                    binding.tvCategoryBackBtn.setText("Classification");
                    getCategories(itemId);

                } else if (type.equals("CATEGORY")) {
                    binding.topPanel.setVisibility(View.VISIBLE);
                    binding.tvCategoryBackBtn.setText("Category");
                    getSubProducts(itemId);
                } else if (type.equals("SUBPRODUCT")) {


                    // move to Product List. Intent to New Activity.
                    Intent intent = new Intent(getActivity(), ProductListActivity.class);
                    intent.putExtra("subProduct", itemId);
                    startActivity(intent);


                }
            }
        });


        getClassifications();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvCategoryRecyclerView.setAdapter(adapter);

        binding.tvCategoryBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = adapter.moveBack();
                if (type.equals("CLASSIFICATION")) {
                    binding.topPanel.setVisibility(View.GONE);
                } else if (type.equals("CATEGORY")) {
                    binding.tvCategoryBackBtn.setText("Classification");
                }
            }
        });
    }

    private void getClassifications() {
        Call<ArrayList<Classification>> call = parent.mainApi.getClassifications();


        call.enqueue(new RequestCallback<ArrayList<Classification>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Classification>> call, Response<ArrayList<Classification>> response) {
                classificationArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Classification>> call, Throwable t) {

            }
        });
    }

    private void getCategories(int classificationId) {
        adapter.setType("CATEGORY");


        Call<ArrayList<Category>> call = parent.mainApi.getCategories(classificationId);
        call.enqueue(new RequestCallback<ArrayList<Category>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                categoryArrayList.clear();
                categoryArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Category>> call, Throwable t) {

            }
        });
    }

    private void getSubProducts(int categoryId) {
        adapter.setType("SUBPRODUCT");

        Call<ArrayList<SubProduct>> call = parent.mainApi.getSubProducts(categoryId);

        call.enqueue(new RequestCallback<ArrayList<SubProduct>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<SubProduct>> call, Response<ArrayList<SubProduct>> response) {
                subProductArrayList.clear();
                subProductArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<SubProduct>> call, Throwable t) {

            }
        });

    }
}
