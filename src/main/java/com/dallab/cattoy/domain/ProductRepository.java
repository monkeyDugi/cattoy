package com.dallab.cattoy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// interface, class 아무거나 상관없다.
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product save(Product product);
}
