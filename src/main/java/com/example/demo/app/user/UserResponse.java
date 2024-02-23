package com.example.demo.app.user;

import com.example.demo.abstract_crud.CrudResponse;
import com.example.demo.app.car.CarResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserResponse implements CrudResponse<Long> {

    private Long id;
    private String name;
    private Integer age;
    @JsonIgnoreProperties("person") private List<CarResponse> cars;
}
