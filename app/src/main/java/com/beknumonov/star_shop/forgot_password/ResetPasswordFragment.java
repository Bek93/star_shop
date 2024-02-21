package com.beknumonov.star_shop.forgot_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentResetPasswordBinding;
import com.beknumonov.star_shop.databinding.FragmentVerifyCodeBinding;

public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding> {
    @Override
    protected FragmentResetPasswordBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentResetPasswordBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
