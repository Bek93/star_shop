package com.beknumonov.star_shop.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseDialog;
import com.beknumonov.star_shop.databinding.DialogNoticeBinding;

public class NoticeDialog extends BaseDialog<DialogNoticeBinding> {


    public static NoticeDialog getInstance(String message) {
        NoticeDialog dialog = new NoticeDialog();
        Bundle basket = new Bundle();
        basket.putString("message", message);
        dialog.setArguments(basket);
        return dialog;
    }

    @Override
    protected DialogNoticeBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttachParent) {
        return DialogNoticeBinding.inflate(inflater, parent, toAttachParent);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String message = getArguments().getString("message");
            binding.tvMessage.setText(message);
        }


        binding.closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
