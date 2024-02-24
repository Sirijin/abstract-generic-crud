package com.example.demo.variant_1.app.user;

import com.example.demo.variant_1.abstract_crud.marker.CrudRequest;
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
