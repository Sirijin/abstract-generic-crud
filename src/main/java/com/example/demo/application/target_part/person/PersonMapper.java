package com.example.demo.application.target_part.person;

import com.example.demo.application.abstract_part.CommonMapper;
import com.example.demo.application.target_part.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = CommonMapper.class)
public abstract class PersonMapper implements CommonMapper<Person, PersonDto> {

    @Mapping(target = "projectIds", source = "person", qualifiedByName = "toProjectIds")
    public abstract PersonDto toDto(Person person);

    @Named("toProjectIds")
    public Set<Long> toProjectIds(Person entity) {
        return entity.getProjects().stream().map(Project::getId).collect(Collectors.toSet());
    }
}
