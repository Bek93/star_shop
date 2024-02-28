package com.beknumonov.star_shop.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;
import com.beknumonov.star_shop.databinding.FragmentProductsBinding;

public class ProductsFragment extends BaseFragment<FragmentProductsBinding> {
    @Override
    protected FragmentProductsBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProductsBinding.inflate(inflater, parent, toAttach);
    }
}
