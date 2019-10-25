package com.dallab.cattoy.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// interface, class 아무거나 상관없다.
public interface ProductRepository {

    public List<Product> findAll();

    public void save(Product product);
}
