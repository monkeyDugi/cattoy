package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdcutService {

    private  ProductRepository productRepository;

    private  List<Product> products = new ArrayList<>();

    public ProdcutService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    public void addProduct(String name) {

        Product product = Product.builder().name(name).build();

        productRepository.save(product);
    }
}
