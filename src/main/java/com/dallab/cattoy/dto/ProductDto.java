package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {


//  @Mapping dozer의 Mapping으로 객체(Product)간의 매핑을 위함
    @Mapping("id")
    private Long id;
    @Mapping("name")
    private String name;
    @Mapping("maker")
    private  String maker;
    @Mapping("price")
    private  Integer price;
}
