package com.example.demo.app.car;

import com.example.demo.abstract_crud.marker.CrudResponse;
import com.example.demo.app.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class CarResponse implements CrudResponse<Long> {

    private Long id;
    private String name;
    @JsonIgnoreProperties("cars") private UserDto user;
}
