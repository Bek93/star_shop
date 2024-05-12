package com.beknumonov.star_shop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("product")
    @Expose
    public int product;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("is_main")
    @Expose
    public boolean isMain;
    @SerializedName("date_created")
    @Expose
    public String dateCreated;

    public Image(int id, int product, String image, boolean isMain, String dateCreated) {
        this.id = id;
        this.product = product;
        this.image = image;
        this.isMain = isMain;
        this.dateCreated = dateCreated;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
