package com.example.demo.product.client;

import com.example.demo.product.config.LoadBalancerConfiguration;
import com.example.demo.product.entity.InventoryDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@FeignClient(name = "INVENTORY-SERVICE")
@LoadBalancerClient(name = "INVENTORY-SERVICE", configuration = LoadBalancerConfiguration.class)
public interface InventoryClient {
    @PostMapping(value = "/api/inventories")
    InventoryDTO addInventory(@RequestBody InventoryDTO inventory) throws URISyntaxException;

    @PatchMapping("/api/inventories/{skuCode}")
    InventoryDTO updateInventory(@PathVariable(value = "skuCode", required = false) final String skuCode,
                                 @RequestBody InventoryDTO inventory) throws URISyntaxException;

    @DeleteMapping("/api/inventories/{skuCode}")
    void deleteInventory(@PathVariable String skuCode);
}
