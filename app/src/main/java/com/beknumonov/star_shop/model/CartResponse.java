package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

public class CartResponse {

    @SerializedName("product")
    private int product;


    @SerializedName("quantity")
    private int quantity;


    @SerializedName("option")
    private int option;

    public CartResponse(int product, int quantity, int option) {
        this.product = product;
        this.quantity = quantity;
        this.option = option;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
