package com.example.demo.variant_2.app.project;

import com.example.demo.variant_2.abstract_crud.CommonMapper;
import com.example.demo.variant_2.app.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = CommonMapper.class)
public abstract class ProjectMapper implements CommonMapper<Project, ProjectDto> {

    @Mapping(target = "personIds", source = "project", qualifiedByName = "toPersonIds")
    public abstract ProjectDto toDto(Project project);

    @Named("toPersonIds")
    public Set<Long> toPersonIds(Project entity) {
        return entity.getPersons().stream().map(Person::getId).collect(Collectors.toSet());
    }
}
