package com.example.demo.variant_1.abstract_crud.other;

import com.example.demo.variant_1.abstract_crud.marker.IdentifiableEntity;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class EntityEvent<T extends IdentifiableEntity> implements Serializable {

    private final T entity;

    public EntityEvent(final T entity) {
        this.entity = Objects.requireNonNull(entity, "Parameter 'entity' must not be null!");
    }

    public boolean contain(String simpleClassName) {
        return Objects.requireNonNull(entity, "Parameter 'simpleClassName' must not be null!")
                .getClass()
                .getSimpleName()
                .equals(simpleClassName);
    }

}
