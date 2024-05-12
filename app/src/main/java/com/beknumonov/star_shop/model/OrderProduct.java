package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProduct implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("product")
    private Product product;

    @SerializedName("option")
    private Option option;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price_current")
    private int priceCurrent;


    @SerializedName("price_original")
    private int priceOriginal;

    public int getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public int getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public OrderProduct() {
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
