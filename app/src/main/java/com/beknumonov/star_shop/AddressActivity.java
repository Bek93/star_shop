package com.beknumonov.star_shop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.JavascriptInterface;

import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.databinding.ActivityAddressBinding;

public class AddressActivity extends BaseActivity<ActivityAddressBinding> {
    @Override
    protected ActivityAddressBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddressBinding.inflate(inflater);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webView.loadUrl("http://address.annyong.store/");

        binding.webView.addJavascriptInterface(new WebInterface(), "CallbackWebInterface");


    }

    class WebInterface {

        public WebInterface() {

        }

        @JavascriptInterface
        public void getAddress(String postcode, String address) {
            Log.d("Address", postcode + ", " + address);
            Intent intent = getIntent();
            intent.putExtra("postcode", postcode);
            intent.putExtra("address", address);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
