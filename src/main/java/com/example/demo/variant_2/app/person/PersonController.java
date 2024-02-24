package com.example.demo.variant_2.app.person;

import com.example.demo.variant_2.abstract_crud.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController extends AbstractController<Person, PersonDto, PersonService, PersonMapper> {

    private final PersonService service;

    public PersonController(PersonService service, PersonMapper mapper, PersonService service1) {
        super(service, mapper);
        this.service = service1;
    }

    @PostMapping("/{personId}/projects")
    public ResponseEntity<PersonDto> addProjectsToPerson(@PathVariable Long personId, @RequestBody Set<Long> projectIds) {
        PersonDto personDto = service.addProjectsToPerson(personId, projectIds);
        return ResponseEntity.ok(personDto);
    }

    @DeleteMapping("/{personId}/projects")
    public ResponseEntity<PersonDto> removeProjectsFromPerson(@PathVariable Long personId, @RequestBody Set<Long> projectIds) {
        PersonDto personDto = service.removeProjectsFromPerson(personId, projectIds);
        return ResponseEntity.ok(personDto);
    }
}
