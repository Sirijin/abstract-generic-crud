package com.example.demo.application.target_part.person;

import com.example.demo.application.abstract_part.AbstractService;
import com.example.demo.application.target_part.common.ErrorType;
import com.example.demo.application.target_part.project.Project;
import com.example.demo.application.target_part.project.ProjectRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class PersonService extends AbstractService<Person, PersonDto, PersonRepository, PersonMapper> {

    private final ProjectRepository projectRepository;

    public PersonService(PersonRepository repository, PersonMapper mapper, ProjectRepository projectRepository) {
        super(repository, mapper);
        this.projectRepository = projectRepository;
    }

    @SneakyThrows
    @Transactional
    public PersonDto addProjectsToPerson(Long personId, Set<Long> projectIds) {
        Person person = super.repository.findById(personId).orElseThrow(() ->
                new RuntimeException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), personId))
        );
        Set<Project> projects = StreamSupport.stream(projectRepository.findAllById(projectIds).spliterator(), false)
                .collect(Collectors.toSet());

        if (projects.size() != projectIds.size()) {
            throw new RuntimeException("One or more project entities do not exist");
        }

        person.getProjects().addAll(projects);
        return mapper.toDto(super.repository.save(person));
    }

    @SneakyThrows
    @Transactional
    public PersonDto removeProjectsFromPerson(Long personId, Set<Long> projectIds) {
        Person person = super.repository.findById(personId).orElseThrow(() ->
                new RuntimeException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), personId))
        );

        person.getProjects().removeIf(project -> projectIds.contains(project.getId()));
        return mapper.toDto(super.repository.save(person));
    }
}
