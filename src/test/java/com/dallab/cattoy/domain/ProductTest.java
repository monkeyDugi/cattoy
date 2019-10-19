package com.dallab.cattoy.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductTest {

    private Product product;

    /*생성자를 사용하지 않고 초기화, setter사용하지 않고 초기화
    * 이유는 블로그 참고
    * */
    @Before
    public void initProduct() {
        product = Product.builder()
                .name("쥐돌이x")
                .maker("nike")
                .price(3000)
                .build();
    }

    @Test
    public void creation() {
        assertThat(product.getName()).isEqualTo("쥐돌이x");
        assertThat(product.getMaker()).isEqualTo("nike");
        /*별로 의미없는 거다. 그냥 콤마 찍는거 해본거임*/
        assertThat(product.getPriceWithComma()).isEqualTo("3,000");
    }

    @Test
    public void changeImage() {
        final String imageUrl = "https://images.cat-toy.com/123123.jpg";

        // 명시적으로 이미지는 계속 변경된다는 것을 표현
        product.changeImageUrl(imageUrl);
    }

    @Test
    public void defaultImage() {
        assertThat(product.getImageUrl()).isEqualTo("");
    }
}