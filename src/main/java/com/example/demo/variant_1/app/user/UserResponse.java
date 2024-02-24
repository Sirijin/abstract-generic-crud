package com.example.demo.variant_1.app.user;

import com.example.demo.variant_1.abstract_crud.marker.CrudResponse;
import com.example.demo.variant_1.app.car.CarResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse implements CrudResponse<Long> {

    private Long id;
    private String name;
    private Integer age;
    @JsonIgnoreProperties("person") private List<CarResponse> cars;
}
