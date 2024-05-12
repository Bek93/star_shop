package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String password;

    @SerializedName("first_name")
    private String firstName;


    @SerializedName("last_name")
    private String lastName;

    @SerializedName("access_token")
    private String accessToken;


    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("phone_number")
    private String phone_number;

    private String address;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User(String email, String password, String firstName, String lastName, String phoneNumber, String full_address) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone_number = phoneNumber;
        this.address = full_address;
    }
}
