package com.beknumonov.star_shop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product implements Serializable {


    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("subproduct")
    @Expose
    public int subproduct;
    @SerializedName("price_current")
    @Expose
    public int priceCurrent;
    @SerializedName("price_original")
    @Expose
    public int priceOriginal;
    @SerializedName("quantity")
    @Expose
    public int quantity;
    @SerializedName("infinite")
    @Expose
    public boolean infinite;
    @SerializedName("has_option")
    @Expose
    public boolean hasOption;
    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("is_active")
    @Expose
    public boolean isActive;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("made_in")
    @Expose
    public String madeIn;


    // Product Details Fields


    @SerializedName("images")
    @Expose
    ArrayList<Image> images;


    @SerializedName("color_options")
    @Expose
    ArrayList<ColorOption> colorOptions;

    @SerializedName("size_options")
    @Expose
    ArrayList<SizeOption> sizeOptions;

    @SerializedName("options")
    @Expose
    ArrayList<Option> optionArrayList;

    public ArrayList<Option> getOptionArrayList() {
        return optionArrayList;
    }

    public void setOptionArrayList(ArrayList<Option> optionArrayList) {
        this.optionArrayList = optionArrayList;
    }

    public ArrayList<ColorOption> getColorOptions() {
        return colorOptions;
    }

    public void setColorOptions(ArrayList<ColorOption> colorOptions) {
        this.colorOptions = colorOptions;
    }

    public ArrayList<SizeOption> getSizeOptions() {
        return sizeOptions;
    }

    public void setSizeOptions(ArrayList<SizeOption> sizeOptions) {
        this.sizeOptions = sizeOptions;
    }

    public Product() {
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(int subproduct) {
        this.subproduct = subproduct;
    }

    public int getPriceCurrent() {
        return priceCurrent;
    }

    public String getPriceCurrentAsString() {

        // 100,000₩
        DecimalFormat format = new DecimalFormat("###,###,###₩");
        String price = format.format(priceCurrent);

        return price;
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public int getPriceOriginal() {
        return priceOriginal;
    }

    public String getPriceOriginalAsString() {
        // 100,000₩
        DecimalFormat format = new DecimalFormat("###,###,###₩");
        String price = format.format(priceOriginal);

        return price;
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public boolean isHasOption() {
        return hasOption;
    }

    public void setHasOption(boolean hasOption) {
        this.hasOption = hasOption;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }
}
