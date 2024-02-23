package com.example.demo.abstract_crud;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class CallbackMapper<S, T> implements BiFunction<S, T, T> {

    private final BiFunction<S, T, T> mapper;
    private final BiConsumer<S, T> callback;

    CallbackMapper(@NonNull final BiFunction<S, T, T> mapper, @NonNull final BiConsumer<S, T> callback) {
        this.mapper = Objects.requireNonNull(mapper, "Parameter 'mapper' must not be null!");
        this.callback = Objects.requireNonNull(callback, "Parameter 'callback' must not be null!");
    }

    @NonNull
    @Override
    public T apply(@NonNull final S source, @NonNull final T target) {
        T result = mapper.apply(source, target);
        callback.accept(source, result);
        return result;
    }
}
