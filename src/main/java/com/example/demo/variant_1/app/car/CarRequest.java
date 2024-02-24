package com.example.demo.variant_1.app.car;

import com.example.demo.variant_1.abstract_crud.marker.CrudRequest;
import jakarta.validation.constraints.NotBlank;

public class CarRequest implements CrudRequest {

    @NotBlank
    private String name;
}
