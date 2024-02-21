package com.beknumonov.star_shop.forgot_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.databinding.ActivityForgotPasswordBinding;


/*
        1. Add back icon and star icon to top.
        2. Add fragment container view to rest of parent.
 */


public class ForgotPasswordActivity extends BaseActivity<ActivityForgotPasswordBinding> {
    @Override
    protected ActivityForgotPasswordBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityForgotPasswordBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
