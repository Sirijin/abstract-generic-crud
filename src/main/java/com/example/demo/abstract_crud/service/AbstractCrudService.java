package com.example.demo.abstract_crud.service;

import com.example.demo.abstract_crud.other.EntityEvent;
import com.example.demo.abstract_crud.mapper.CallbackMapper;
import com.example.demo.abstract_crud.mapper.CrudMapper;
import com.example.demo.abstract_crud.marker.CrudRequest;
import com.example.demo.abstract_crud.marker.CrudResponse;
import com.example.demo.abstract_crud.marker.IdentifiableEntity;
import com.example.demo.abstract_crud.repository.CrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.abstract_crud.other.CrudUtils.copyNonNullProperties;

public abstract class AbstractCrudService<
        E extends IdentifiableEntity<ID>,
        ID extends Serializable,
        REQ extends CrudRequest,
        RES extends CrudResponse<ID>>
        implements CrudService<E, ID, REQ, RES> {

    protected final CrudRepo<E, ID> repository;
    protected final CrudMapper<E, REQ, RES> mapper;

    @Autowired
    protected ApplicationEventPublisher publisher;

    protected AbstractCrudService(final CrudRepo<E, ID> repository, final CrudMapper<E, REQ, RES> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @NonNull
    @Override
    public E create(@NonNull final E source) {
        E entity = repository.create(source);

        EntityEvent<E> event = onCreateEvent(entity);
        if (event != null) publisher.publishEvent(event);

        return entity;
    }

    @NonNull
    @Override
    public RES create(@NonNull final REQ source) {
        E entity = mapper.toCreate(source);
        onCreate(source, entity);
        repository.create(entity);

        EntityEvent<E> event = onCreateEvent(entity);
        if (event != null) publisher.publishEvent(event);

        return mapper.toResponse(entity);
    }

    @NonNull
    @Override
    public Optional<E> update(final ID id, final E source) {
        return repository.update(id, source, (s, e) -> copyNonNullProperties(s, e, ignoredProps()))
                .map(entity -> {
                    EntityEvent<E> event = onUpdateEvent(entity);
                    if (event != null) publisher.publishEvent(event);
                    return entity;
                });
    }

    @NonNull
    @Override
    public Optional<RES> update(final ID id, final REQ source) {
        return repository.update(id, source, new CallbackMapper<>(mapper::toUpdate, this::onUpdate))
                .map(entity -> {
                    EntityEvent<E> event = onUpdateEvent(entity);
                    if (event != null) publisher.publishEvent(event);
                    return mapper.toResponse(entity);
                });
    }

    @Override
    public boolean delete(@NonNull final ID id) {
        return repository.del(id).map(deleted -> {
            EntityEvent<E> event = onDeleteEvent(deleted);
            if (event != null) publisher.publishEvent(event);
            return true;
        }).orElse(false);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public E getOneEntity(@NonNull final ID id) {
        return repository.getById(id);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Optional<RES> getOne(@NonNull final ID id) {
        return Optional.of(mapper.toResponse(getOneEntity(id)));
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<E> getAllEntities() {
        return repository.getAll();
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<RES> getAll() {
        return repository.getAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Page<E> getAllEntities(final Pageable pageable) {
        return repository.getAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Page<RES> getAll(final Pageable pageable) {
        return repository.getAllPageable(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<E> getAllEntities(final Sort sort) {
        return repository.getAllSorted(sort);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<RES> getAll(final Sort sort) {
        return repository.getAllSorted(sort).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    protected String[] ignoredProps() {
        return new String[]{"id", "version", "createdAt", "updatedAt"};
    }

    @NonNull
    protected void onCreate(@NonNull REQ request, @NonNull E entity) {
    }

    @NonNull
    protected void onUpdate(@NonNull REQ request, @NonNull E entity) {
    }

    protected EntityEvent<E> onCreateEvent(@NonNull E entity) {
        return null;
    }

    protected EntityEvent<E> onUpdateEvent(@NonNull E entity) {
        return null;
    }

    protected EntityEvent<E> onDeleteEvent(@NonNull E entity) {
        return null;
    }
}
