package com.example.demo.application.abstract_part;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@MapperConfig(
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValueCheckStrategy = ALWAYS,
        nullValuePropertyMappingStrategy = IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CommonMapper<E extends AbstractEntity, D extends Dto> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos(List<E> entities);

    void updateEntity(D dto, @MappingTarget E entity);
}
