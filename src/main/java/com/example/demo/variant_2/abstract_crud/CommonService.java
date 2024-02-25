package com.example.demo.variant_2.abstract_crud;

import com.example.demo.variant_2.app.common.response.BaseResponse;

import java.util.List;
import java.util.Optional;

public interface CommonService<E extends AbstractEntity, D extends Dto> {

    BaseResponse<D> getAll();

    Optional<E> get(Long id);

    Optional<E> save(E entity);

    BaseResponse<D> saveAll(List<E> entities);

    Optional<E> update(E entity);

    Boolean deleteById(Long id);

    Boolean deleteAll();
}
