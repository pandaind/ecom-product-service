package com.example.demo.product.service.mapper;

import com.example.demo.product.entity.InventoryDTO;
import com.example.demo.product.service.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ProductDTO} and its DTO {@link InventoryDTO}.
 */
@Mapper(
        componentModel = "spring",
        uses = {})
public interface InventoryMapper extends ClientDTOMapper<InventoryDTO, ProductDTO> {
}
