package com.example.demo.abstract_crud;

import java.io.Serializable;

public interface IdentifiableEntity<ID extends Serializable> extends Serializable {

    ID getId();
}
