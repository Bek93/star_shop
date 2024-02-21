package com.beknumonov.star_shop.forgot_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.beknumonov.star_shop.R;
import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentVerifyCodeBinding;
import com.beknumonov.star_shop.databinding.FragmentVerifyEmailBinding;

public class VerifyCodeFragment extends BaseFragment<FragmentVerifyCodeBinding> {
    @Override
    protected FragmentVerifyCodeBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentVerifyCodeBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new ResetPasswordFragment()).addToBackStack(null).commit();
            }
        });
    }
}
