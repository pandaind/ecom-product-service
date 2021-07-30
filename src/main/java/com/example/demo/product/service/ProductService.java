package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import com.example.demo.product.service.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findAllByCategory(String category);
    Optional<ProductDTO> findById(Long id);
    List<ProductDTO> findAllByName(String name);
    ProductDTO add(ProductDTO productDTO);
    void delete(Long productId);
}
