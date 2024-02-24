package com.example.demo.variant_2.app.person;

import com.example.demo.variant_2.app.common.ErrorType;
import com.example.demo.variant_2.app.common.SampleException;
import com.example.demo.variant_2.abstract_crud.AbstractService;
import com.example.demo.variant_2.app.project.Project;
import com.example.demo.variant_2.app.project.ProjectRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class PersonService extends AbstractService<Person, PersonRepository, PersonMapper> {

    private final ProjectRepository projectRepository;

    public PersonService(PersonRepository repository, PersonMapper mapper, ProjectRepository projectRepository) {
        super(repository, mapper);
        this.projectRepository = projectRepository;
    }

    @SneakyThrows
    @Transactional
    public PersonDto addProjectsToPerson(Long personId, Set<Long> projectIds) {
        Person person = super.repository.findById(personId).orElseThrow(() ->
                new SampleException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), personId))
        );
        Set<Project> projects = StreamSupport.stream(projectRepository.findAllById(projectIds).spliterator(), false)
                .collect(Collectors.toSet());

        if (projects.size() != projectIds.size()) {
            throw new SampleException("One or more project entities do not exist");
        }

        person.getProjects().addAll(projects);
        return mapper.toDto(super.repository.save(person));
    }

    @SneakyThrows
    @Transactional
    public PersonDto removeProjectsFromPerson(Long personId, Set<Long> projectIds) {
        Person person = super.repository.findById(personId).orElseThrow(() ->
                new SampleException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), personId))
        );

        person.getProjects().removeIf(project -> projectIds.contains(project.getId()));
        return mapper.toDto(super.repository.save(person));
    }
}
