package com.example.demo.abstract_crud;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.io.Serializable;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@MapperConfig(
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValueCheckStrategy = ALWAYS,
        nullValuePropertyMappingStrategy = IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CrudMapper<T extends IdentifiableEntity<? extends Serializable>, REQ extends CrudRequest, RES extends CrudResponse<? extends Serializable>> {

    @NonNull
    T toCreate(@NonNull REQ request);

    @NonNull
    T toUpdate(@NonNull REQ request, @MappingTarget @NonNull T target);

    @NonNull
    RES toResponse(@NonNull T entity);
}
