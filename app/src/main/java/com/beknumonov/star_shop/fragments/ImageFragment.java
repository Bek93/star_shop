package com.beknumonov.star_shop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentImageBinding;
import com.bumptech.glide.Glide;

public class ImageFragment extends BaseFragment<FragmentImageBinding> {
    @Override
    protected FragmentImageBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentImageBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String image = getArguments().getString("imageUrl");

        if (image != null && !image.isEmpty()) {

            Glide.with(binding.ivShowImage).load(image).into(binding.ivShowImage);
        }
    }
}
