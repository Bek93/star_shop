package com.beknumonov.star_shop.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseDialog<VB extends ViewBinding> extends DialogFragment {


    protected VB binding;

    protected abstract VB inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttachParent);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = inflateView(inflater, container, false);
        return binding.getRoot();
    }
}
