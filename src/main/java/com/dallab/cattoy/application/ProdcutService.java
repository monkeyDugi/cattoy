package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional // 나중에 뭐 좋다고 하는데 지금은 신경쓰지 말자
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

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
