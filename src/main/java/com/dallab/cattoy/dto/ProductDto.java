package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

@Data
public class ProductDto {


//  Entity와 mapping : 꼭 없어도 되는건가? 명시적인건가?
    @Mapping("name")
    private String name;
    @Mapping("maker")
    private  String maker;
    @Mapping("price")
    private  Integer price;
}
