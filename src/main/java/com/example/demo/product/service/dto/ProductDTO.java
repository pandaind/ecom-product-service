package com.example.demo.product.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductDTO implements Serializable {
    private Long id;
    private String productName;
    private BigDecimal price;
    private String description;
    private String category;
    private String skuCode;
}
