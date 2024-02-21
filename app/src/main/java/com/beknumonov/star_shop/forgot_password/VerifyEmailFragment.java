package com.beknumonov.star_shop.forgot_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.beknumonov.star_shop.R;
import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.databinding.FragmentVerifyEmailBinding;

public class VerifyEmailFragment extends BaseFragment<FragmentVerifyEmailBinding> {
    @Override
    protected FragmentVerifyEmailBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentVerifyEmailBinding.inflate(inflater, parent, toAttach);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new VerifyCodeFragment()).addToBackStack(null).commit();
            }
        });
    }
}
