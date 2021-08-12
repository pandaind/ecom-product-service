package com.example.demo.product.web.rest;

import com.example.demo.product.client.InventoryClient;
import com.example.demo.product.service.ProductService;
import com.example.demo.product.service.dto.ProductDTO;
import com.example.demo.product.service.mapper.InventoryMapper;
import com.example.demo.product.web.rest.errors.BadRequestAlertException;
import com.example.demo.product.web.rest.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminProductResource {
    private static final String ENTITY_NAME = "Product";
    private final ProductService service;
    private final InventoryClient inventoryClient;
    private final InventoryMapper inventoryMapper;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    public AdminProductResource(ProductService service, InventoryClient inventoryClient, InventoryMapper inventoryMapper) {
        this.service = service;
        this.inventoryClient = inventoryClient;
        this.inventoryMapper = inventoryMapper;
    }

    @PostMapping(value = "/products")
    @Transactional
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }

        ProductDTO result = this.service.add(product);

        this.inventoryClient.addInventory(inventoryMapper.toClientDto(product));

        return ResponseEntity.created(new URI("/admin/products/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping(value = "/products/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        log.debug("REST request to delete Product with id : {}", id);
        // Get the sku code
        Optional<String> skuCode = this.service.findById(id).map(ProductDTO::getSkuCode);
        this.service.delete(id);
        log.info("REST request to delete Inventory with skuCode : {}", skuCode.get());
        skuCode.ifPresent(this.inventoryClient::deleteInventory);

        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName,
                        false, ENTITY_NAME, id.toString())).build();
    }
}
