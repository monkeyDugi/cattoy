package com.dallab.cattoy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.NumberFormat;

@Entity // JPA를 사용하려면 해줘야 한다. 알아보자
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // JAP 사용하려면 해주어야 한다는데 알아보자.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
