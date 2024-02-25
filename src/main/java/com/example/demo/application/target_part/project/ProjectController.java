package com.example.demo.application.target_part.project;

import com.example.demo.application.abstract_part.AbstractController;
import com.example.demo.application.target_part.common.exceptionHandler.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, ProjectDto, ProjectService, ProjectMapper> {

    public ProjectController(ProjectService service, ProjectMapper mapper) {
        super(service, mapper);
    }

    @Operation(description = "Add some people to project", summary = "Add some people to project", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProjectDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{projectId}/persons")
    public ResponseEntity<ProjectDto> addPersonsToProject(@PathVariable Long projectId, @RequestBody Set<Long> personIds) {
        ProjectDto projectDto = service.addPersonsToProject(projectId, personIds);
        return ResponseEntity.ok(projectDto);
    }

    @Operation(description = "Remove some people from project", summary = "Remove some people from project", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProjectDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{projectId}/persons")
    public ResponseEntity<ProjectDto> removePersonsFromProject(@PathVariable Long projectId, @RequestBody Set<Long> personIds) {
        ProjectDto projectDto = service.removePersonsFromProject(projectId, personIds);
        return ResponseEntity.ok(projectDto);
    }
}
