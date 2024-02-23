package com.example.demo.app.user;

import com.example.demo.abstract_crud.marker.CrudRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest implements CrudRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}
