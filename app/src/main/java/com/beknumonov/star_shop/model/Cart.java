package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

public class Cart {

    @SerializedName("id")
    private int id;

    @SerializedName("product")
    private Product product;

    @SerializedName("option")
    private Option option;

    @SerializedName("quantity")
    private int quantity;


    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
