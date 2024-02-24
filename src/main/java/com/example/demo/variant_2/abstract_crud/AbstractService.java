package com.example.demo.variant_2.abstract_crud;

import com.example.demo.variant_2.app.common.ErrorType;
import com.example.demo.variant_2.app.common.SampleException;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>, M extends CommonMapper<E, ? extends Dto>>
        implements CommonService<E> {

    protected final R repository;
    protected final M mapper;

    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public List<E> saveAll(List<E> entities) {
        return Lists.newArrayList(repository.saveAll(entities));
    }

    @Override
    public Optional<E> update(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<E> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Boolean deleteById(Long id) {
        E entity = get(id)
                .orElseThrow(() -> new SampleException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)));
        repository.delete(entity);
        return repository.findById(entity.getId()).isEmpty();
    }

    @Override
    public Boolean deleteAll() {
        repository.deleteAll();
        return Lists.newArrayList(repository.findAll()).isEmpty();
    }
}
