package com.beknumonov.star_shop.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;
import com.beknumonov.star_shop.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    @Override
    protected FragmentProfileBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProfileBinding.inflate(inflater, parent, toAttach);
    }
}
