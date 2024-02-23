package com.example.demo.app.car;

import com.example.demo.abstract_crud.CrudRequest;
import jakarta.validation.constraints.NotBlank;

public class CarRequest implements CrudRequest {

    @NotBlank
    private String name;
}
