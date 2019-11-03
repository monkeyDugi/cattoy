package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProductDto {


//  @Mapping dozer의 Mapping으로 객체(Product)간의 매핑을 위함
    @Mapping("id")
    private Long id;

    @NotBlank
    @Mapping("name")
    private String name;

    @Mapping("maker")
    private  String maker;

    @Min(0)
    @Mapping("price")
    private  Integer price;
}
