package com.example.demo.variant_2.app.project;

import com.example.demo.variant_2.abstract_crud.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, ProjectDto, ProjectService, ProjectMapper> {

    private final ProjectService service;

    public ProjectController(ProjectService service, ProjectMapper mapper, ProjectService service1) {
        super(service, mapper);
        this.service = service1;
    }

    @PostMapping("/{projectId}/persons")
    public ResponseEntity<ProjectDto> addPersonsToProject(@PathVariable Long projectId, @RequestBody Set<Long> personIds) {
        ProjectDto projectDto = service.addPersonsToProject(projectId, personIds);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{projectId}/persons")
    public ResponseEntity<ProjectDto> removePersonsFromProject(@PathVariable Long projectId, @RequestBody Set<Long> personIds) {
        ProjectDto projectDto = service.removePersonsFromProject(projectId, personIds);
        return ResponseEntity.ok(projectDto);
    }
}
