package com.beknumonov.star_shop.utils;

public enum Tab {

    HOME("HOME"), PRODUCTS("PRODUCTS"), CART("CART"), PROFILE("PROFILE");


    String tag;

    Tab(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
