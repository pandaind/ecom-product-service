package com.example.demo.product.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <C> - ClientDTO type parameter.
 * @param <E> - EntityDTO type parameter.
 */
public interface ClientDTOMapper<C, E> {
    E toEntityDTO(C dto);

    C toClientDto(E entity);

    List<E> toEntityDTO(List<C> dtoList);

    List<C> toClientDto(List<E> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entityDTO, C clientDTO);
}
