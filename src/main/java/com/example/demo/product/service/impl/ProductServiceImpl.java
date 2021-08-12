package com.example.demo.product.service.impl;

import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.ProductService;
import com.example.demo.product.service.dto.ProductDTO;
import com.example.demo.product.service.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        log.debug("Request to get all Products");
        return this.mapper.toDto(this.productRepository.findAll());
    }

    @Override
    public List<ProductDTO> findAllByCategory(String category) {
        return this.mapper.toDto(this.productRepository.findAllByCategory(category));
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return this.productRepository.findById(id).map(this.mapper::toDto);
    }

    @Override
    public List<ProductDTO> findAllByName(String name) {
        return this.mapper.toDto(this.productRepository.findAllByProductName(name));
    }

    @Override
    public ProductDTO add(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = this.mapper.toEntity(productDTO);
        product = this.productRepository.save(product);
        return this.mapper.toDto(product);
    }

    @Override
    public void delete(Long productId) {
        log.debug("Request to delete Product : {}", productId);
        this.productRepository.deleteById(productId);
    }
}
