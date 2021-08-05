package com.example.demo.product.service.mapper;

import com.example.demo.product.entity.Product;
import com.example.demo.product.service.dto.ProductDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(
        componentModel = "spring",
        uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoId(Product product);
}
