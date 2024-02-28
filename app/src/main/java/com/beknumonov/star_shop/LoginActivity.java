package com.beknumonov.star_shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.ActivityLoginBinding;
import com.beknumonov.star_shop.dialog.MyDialogs;
import com.beknumonov.star_shop.dialog.NoticeDialog;
import com.beknumonov.star_shop.forgot_password.ForgotPasswordActivity;
import com.beknumonov.star_shop.model.User;

import retrofit2.Call;
import retrofit2.Response;

/*
    Action Items                                Status
    1. Valid email                               DONE
    2. Show and Hide password                    DONE
    3. Move to Sign Up page                      DONE
    4. Login.
        5. Link with Login API.
        6. Show success or failed dialog.
*/


public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("EditText", s.toString() + ", start=" + start + ", count=" + count + ", after=" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("EditText", s.toString() + ", start=" + start + ", count=" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("EditText", s.toString());
                String email = s.toString();
                boolean isEmailValid = isEmailValid(email);
                binding.icVerifiedEmail.setVisibility(isEmailValid ? View.VISIBLE : View.INVISIBLE);
            }
        });

        // drawable: showHidePasswordBtn state_selected=true  show eye icon
        // drawable: showHidePasswordBtn state_selected=false  show close eye icon

        binding.showHidePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.showHidePasswordBtn.isSelected();
                // !isSelected (next flip) is true, show password
                if (!isSelected) {
                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                } else  // !isSelected (next flip) is false, hide password
                {
                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.showHidePasswordBtn.setSelected(!isSelected);
            }
        });

        binding.moveToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.etEmail.getText().toString();

                if (!isEmailValid(email)) {
                    // show alert dialog
                    return;
                }

                String password = binding.etPassword.getText().toString();

                if (password.length() < 6) {
                    // show alert dialog
                    return;
                }

                User user = new User(email, password);
                Call<User> call = mainApi.login(user);
                call.enqueue(new RequestCallback<User>() {
                    @Override
                    protected void onResponseSuccess(Call<User> call, Response<User> response) {
                        User loggedInUser = response.body();
                        // Save data to preference.
                        preferenceManager.setValue("isLoggedIn", true);
                        preferenceManager.setValue("user", loggedInUser);
                        preferenceManager.setValue("email", email);
                        preferenceManager.setValue("password", password);
                        preferenceManager.setValue("accessToken", loggedInUser.getAccessToken());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    protected void onResponseFailed(Call<User> call, Throwable t) {
                        MyDialogs.showNoticeDialog(getSupportFragmentManager(), "Username or Password is incorrect!");
                    }
                });


            }
        });


    }


    private boolean isEmailValid(String email) {

        /*
          beknumonov@gmail.com  VALID
          * Must contains '@'
          * Must contains '.' after '@'
          * '.' should not be last characters.

          beknumonov@.com
        */
        boolean isEmailValid = true;

        if (!email.contains("@"))
            return false;


        String emailTail = email.substring(email.indexOf('@'));
        // any text after '@' should contain '.'
        if (!emailTail.contains("."))
            return false;

        // @.
        if (emailTail.indexOf('.') == (emailTail.indexOf('@') + 1))
            return false;

        // beknumonov@gmail.
        if (email.lastIndexOf('.') == (email.length() - 1))
            return false;


        return isEmailValid;
    }
}
