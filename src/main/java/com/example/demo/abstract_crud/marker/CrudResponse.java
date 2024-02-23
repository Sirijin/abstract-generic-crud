package com.example.demo.abstract_crud.marker;

import org.springframework.lang.NonNull;

import java.io.Serializable;

public interface CrudResponse<ID extends Serializable> extends Serializable {
    @NonNull
    ID getId();
}
