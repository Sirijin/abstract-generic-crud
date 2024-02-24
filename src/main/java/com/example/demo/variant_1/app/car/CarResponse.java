package com.example.demo.variant_1.app.car;

import com.example.demo.variant_1.abstract_crud.marker.CrudResponse;
import com.example.demo.variant_1.app.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class CarResponse implements CrudResponse<Long> {

    private Long id;
    private String name;
    @JsonIgnoreProperties("cars") private UserDto user;
}
