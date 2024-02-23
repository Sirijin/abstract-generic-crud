package com.example.demo.abstract_crud.controller;

import com.example.demo.abstract_crud.marker.CrudRequest;
import com.example.demo.abstract_crud.marker.CrudResponse;
import com.example.demo.abstract_crud.marker.IdentifiableEntity;
import com.example.demo.abstract_crud.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudController<
        E extends IdentifiableEntity<ID>,
        ID extends Serializable,
        REQ extends CrudRequest,
        RES extends CrudResponse<ID>> {

    protected final CrudService<E, ID, REQ, RES> service;

    public AbstractCrudController(@NonNull final CrudService<E, ID, REQ, RES> service) {
        this.service = service;
    }

    @NonNull
    public ResponseEntity<Page<RES>> getAll(@NonNull final Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @NonNull
    public ResponseEntity<List<RES>> getAll(@NonNull final Sort sort) {
        return ResponseEntity.ok(service.getAll(sort));
    }

    @NonNull
    public ResponseEntity<List<RES>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @NonNull
    public ResponseEntity<RES> getOne(@NonNull final ID id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @NonNull
    public ResponseEntity<RES> create(@NonNull final REQ request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @NonNull
    public ResponseEntity<RES> update(@NonNull final ID id, @NonNull final REQ request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @NonNull
    public ResponseEntity<?> delete(@NonNull final ID id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
