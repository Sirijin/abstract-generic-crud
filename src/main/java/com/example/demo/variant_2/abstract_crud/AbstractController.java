package com.example.demo.variant_2.abstract_crud;

import com.example.demo.variant_2.app.common.ErrorType;
import com.example.demo.variant_2.app.common.SampleException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractController<E extends AbstractEntity, D extends Dto, S extends CommonService<E>, M extends CommonMapper<E, D>> implements CommonController<D> {

    private final S service;
    private final M mapper;

    @Override
    public ResponseEntity<D> save(@RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        E savedEntity = service.save(entity).orElseThrow(() -> new SampleException(
                String.format(ErrorType.ENTITY_NOT_SAVED.getDescription(), entity.toString())
        ));
        return ResponseEntity.ok(mapper.toDto(savedEntity));
    }

    @Override
    public ResponseEntity<List<D>> saveAll(@RequestBody List<D> dtos) {
        List<E> entities = mapper.toEntities(dtos);
        List<E> savedEntities = service.saveAll(entities);
        return ResponseEntity.ok(mapper.toDtos(savedEntities));
    }

    @Override
    public ResponseEntity<D> update(@RequestBody D dto) {
        E updatedEntity = service.get(mapper.toEntity(dto).getId())
                .map(entity -> {
                    mapper.updateEntity(dto, entity);
                    return service.save(entity).orElseThrow(() -> new SampleException(
                            String.format(ErrorType.ENTITY_NOT_UPDATED.getDescription(), entity)
                    ));
                }).orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), dto)
                ));
        return ResponseEntity.ok(mapper.toDto(updatedEntity));
    }

    @Override
    public ResponseEntity<D> get(@RequestParam Long id) {
        E entity = service.get(id).orElseThrow(() -> new SampleException(
                String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)
        ));
        return ResponseEntity.ok(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<List<D>> getAll() {
        List<E> entities = service.getAll();
        return ResponseEntity.ok(mapper.toDtos(entities));
    }

    @Override
    public Boolean delete(@RequestParam Long id) {
        return service.deleteById(id);
    }

    @Override
    public Boolean deleteAll() {
        return service.deleteAll();
    }
}