package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.ProdcutService;
import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.dto.ProductDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

//  메인에서 설정한 Dozer Bean을 사용가능
    @Autowired
    Mapper mapper;

    @Autowired
    private ProdcutService productService;

    @GetMapping("/products")
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

//      리스트를 Dto에 mapping하여 뿌려준다. forEach같은거 쓸 필요없이
        return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/product")
    public void save() {
        productService.addProduct("쥐돌이");
    }
}
