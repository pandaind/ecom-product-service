package com.example.demo.product.web.rest;

import com.example.demo.product.service.ProductService;
import com.example.demo.product.service.dto.ProductDTO;
import com.example.demo.product.web.rest.errors.BadRequestAlertException;
import com.example.demo.product.web.rest.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminProductResource {
    private static final String ENTITY_NAME = "Product";
    @Value("${spring.application.name}")
    private String applicationName;

    private final ProductService service;

    @Autowired
    public AdminProductResource(ProductService service) {
        this.service = service;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }

        ProductDTO result = this.service.add(product);

        return ResponseEntity.created(new URI("/admin/products/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        log.debug("REST request to delete Product with id : {}", id);
        this.service.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName,
                        false, ENTITY_NAME, id.toString())).build();
    }
}
