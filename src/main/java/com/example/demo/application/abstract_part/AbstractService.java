package com.example.demo.application.abstract_part;

import com.example.demo.application.target_part.common.ErrorType;
import com.example.demo.application.target_part.common.response.BaseResponse;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public abstract class AbstractService<E extends AbstractEntity, D extends Dto, R extends CommonRepository<E>, M extends CommonMapper<E, D>>
        implements CommonService<E, D> {

    protected final R repository;
    protected final M mapper;

    @SneakyThrows
    @Override
    public BaseResponse<D> getAll() {
        List<D> items = StreamSupport.stream(repository.findAll().spliterator(), false).map(mapper::toDto).toList();
        long total = repository.count();
        return new BaseResponse<>(items, total);
    }

    @SneakyThrows
    @Override
    public Optional<E> get(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @SneakyThrows
    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Transactional
    @SneakyThrows
    @Override
    public BaseResponse<D> saveAll(List<E> entities) {
        List<D> items = StreamSupport.stream(repository.saveAll(entities).spliterator(), false).map(mapper::toDto).toList();
        long totalSaved = entities.size();
        return new BaseResponse<>(items, totalSaved);
    }

    @Transactional
    @SneakyThrows
    @Override
    public Optional<E> update(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Transactional
    @SneakyThrows
    @Override
    public Boolean deleteById(Long id) {
        E entity = get(id)
                .orElseThrow(() -> new RuntimeException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)));
        repository.delete(entity);
        return repository.findById(entity.getId()).isEmpty();
    }

    @Transactional
    @SneakyThrows
    @Override
    public Boolean deleteAll() {
        repository.deleteAll();
        return Lists.newArrayList(repository.findAll()).isEmpty();
    }
}
