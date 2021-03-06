package com.example.demo.product.repository;

import com.example.demo.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(String category);

    List<Product> findAllByProductName(String name);
}
