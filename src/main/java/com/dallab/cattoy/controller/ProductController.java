package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.ProdcutService;
import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.dto.ProductDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

//      리스트를 Dto에 mapping하여 뿌려준다. 객체간의 매핑
        return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductDto detail(@PathVariable("id") Long id) {
        Product product = productService.getProducts(id);

        return mapper.map(product, ProductDto.class);
    }

    // @RequestBody JSON 받기
    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) throws URISyntaxException {
        Product product = productService.addProduct(
                mapper.map(productDto, Product.class));

        // POST 시 반드시 헤더에 URL 정보가 들어가야 한다??
        // 상태, 에러, URL등 을 설정하고 리턴 받기 위한 것인가? ResponseEntity
        URI location = new URI("/products/" + product.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/products/{id}")
    public void destroy(@PathVariable("id") Long id) {
        productService.removeProduct(id);
    }
}
