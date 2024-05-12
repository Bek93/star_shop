package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderRequest {

    @SerializedName("carts")
    private ArrayList<Integer> carts;

    public OrderRequest(ArrayList<Integer> carts) {
        this.carts = carts;
    }

    public OrderRequest() {
    }

    public ArrayList<Integer> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Integer> carts) {
        this.carts = carts;
    }
}
