package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("image")
    private String image;

    @SerializedName("is_active")
    private boolean isActive;

    public Banner() {
    }

    public Banner(String image, boolean isActive) {
        this.image = image;
        this.isActive = isActive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
