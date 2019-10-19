package com.dallab.cattoy.domain;

import lombok.Builder;
import lombok.Getter;

import java.text.NumberFormat;


@Builder
public class Product {

    @Getter
    private String name;
    @Getter
    private String maker;
    private Integer price;
    private String imagUrl;

    public String getPriceWithComma() {
        return NumberFormat.getInstance().format(price);
    }

    public String getImageUrl() {
        return imagUrl == null ? "" : imagUrl;
    }

    public void changeImageUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }
}
