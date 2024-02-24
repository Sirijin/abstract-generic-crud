package com.example.demo.variant_1.abstract_crud.service;

import com.example.demo.variant_1.abstract_crud.marker.CrudRequest;
import com.example.demo.variant_1.abstract_crud.marker.CrudResponse;
import com.example.demo.variant_1.abstract_crud.marker.IdentifiableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<
        E extends IdentifiableEntity<ID>,
        ID extends Serializable,
        REQ extends CrudRequest,
        RES extends CrudResponse<ID>> {

    @NonNull
    RES create(@NonNull REQ source);

    @NonNull
    E create(@NonNull E source);

    @NonNull
    Optional<RES> update(@NonNull ID id, @NonNull REQ source);

    @NonNull
    Optional<E> update(@NonNull ID id, @NonNull E source);

    @NonNull
    boolean delete(@NonNull ID id);

    @NonNull
    Optional<RES> getOne(@NonNull ID id);

    @NonNull
    E getOneEntity(@NonNull ID id);

    @NonNull
    List<RES> getAll();

    @NonNull
    List<E> getAllEntities();

    @NonNull
    Page<RES> getAll(@NonNull Pageable pageable);

    @NonNull
    Page<E> getAllEntities(@NonNull Pageable pageable);

    @NonNull
    List<RES> getAll(@NonNull Sort sort);

    @NonNull
    List<E> getAllEntities(@NonNull Sort sort);
}