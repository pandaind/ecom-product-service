package com.example.demo.product.web.rest;

import com.example.demo.product.service.ProductService;
import com.example.demo.product.service.dto.ProductDTO;
import com.example.demo.product.web.rest.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProductResource {

    private final ProductService service;

    @Autowired
    public ProductResource(ProductService service) {
        this.service = service;
    }

    /**
     * {@code GET /products}: get all the products
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.debug("REST request to get all products");
        var products = service.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/products", params = "category")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategory(@RequestParam("category") String category) {
        log.debug("REST request to get all products by category {}", category);
        var products = service.findAllByCategory(category);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/products", params = "name")
    public ResponseEntity<List<ProductDTO>> getAllProductByName(@RequestParam("name") String name) {
        log.debug("REST request to get all products by name {}", name);
        var products = service.findAllByName(name);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDTO> getOneProductById(@PathVariable("id") long id) {
        log.debug("REST request to get one product by id {}", id);
        var product = service.findById(id);
        return ResponseUtil.wrapNotFound(product);
    }
}
