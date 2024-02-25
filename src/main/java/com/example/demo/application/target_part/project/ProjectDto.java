package com.example.demo.application.target_part.project;

import com.example.demo.application.abstract_part.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectDto implements Dto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String brand;
    private String model;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Long> personIds;
}
