package com.example.demo.variant_2.app.project;

import com.example.demo.variant_2.abstract_crud.AbstractService;
import com.example.demo.variant_2.app.common.ErrorType;
import com.example.demo.variant_2.app.person.Person;
import com.example.demo.variant_2.app.person.PersonRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ProjectService extends AbstractService<Project, ProjectDto, ProjectRepository, ProjectMapper> {

    private final PersonRepository personRepository;

    public ProjectService(ProjectRepository repository, ProjectMapper mapper, PersonRepository personRepository) {
        super(repository, mapper);
        this.personRepository = personRepository;
    }

    @SneakyThrows
    @Transactional
    public ProjectDto addPersonsToProject(Long projectId, Set<Long> personIds) {
        Project project = super.repository.findById(projectId).orElseThrow(() ->
                new RuntimeException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), projectId))
        );
        Set<Person> persons = StreamSupport.stream(personRepository.findAllById(personIds).spliterator(), false)
                .collect(Collectors.toSet());

        if (persons.size() != personIds.size()) {
            throw new RuntimeException("One or more person entities do not exist");
        }

        project.getPersons().addAll(persons);
        return mapper.toDto(super.repository.save(project));
    }

    @SneakyThrows
    @Transactional
    public ProjectDto removePersonsFromProject(Long projectId, Set<Long> personIds) {
        Project project = super.repository.findById(projectId).orElseThrow(() ->
                new RuntimeException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), projectId))
        );

        project.getPersons().removeIf(person -> personIds.contains(person.getId()));
        return mapper.toDto(super.repository.save(project));
    }
}
