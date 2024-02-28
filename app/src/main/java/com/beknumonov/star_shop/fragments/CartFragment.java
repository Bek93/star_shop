package com.beknumonov.star_shop.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentCartBinding;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    @Override
    protected FragmentCartBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentCartBinding.inflate(inflater, parent, toAttach);
    }
}
