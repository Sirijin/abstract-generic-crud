package com.example.demo.abstract_crud.repository;

import com.example.demo.abstract_crud.marker.IdentifiableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public interface CrudRepo<T extends IdentifiableEntity<ID>, ID extends Serializable> {

    @NonNull
    T create(@NonNull T entity);

    @NonNull
    <S> Optional<T> update(@NonNull ID id, @NonNull S source, @NonNull BiFunction<S, T, T> mapper);

    @NonNull
    Optional<T> del(@NonNull ID id);

    @NonNull
    T getById(@NonNull ID id);

    @NonNull
    List<T> getAll();

    @NonNull
    Page<T> getAllPageable(@NonNull Pageable pageable);

    @NonNull
    List<T> getAllSorted(@NonNull Sort sort);
}
