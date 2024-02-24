package com.example.demo.variant_2.app.person;

import com.example.demo.variant_2.abstract_crud.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class PersonDto implements Dto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String phone;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Long> projectIds;
}
