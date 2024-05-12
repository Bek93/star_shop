package com.beknumonov.star_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Option implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("color_option")
    private ColorOption colorOption;
    @SerializedName("size_option")
    private SizeOption sizeOption;

    public Option() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorOption getColorOption() {
        return colorOption;
    }

    public void setColorOption(ColorOption colorOption) {
        this.colorOption = colorOption;
    }

    public SizeOption getSizeOption() {
        return sizeOption;
    }

    public void setSizeOption(SizeOption sizeOption) {
        this.sizeOption = sizeOption;
    }
}
