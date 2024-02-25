package com.example.demo.application.target_part.person;

import com.example.demo.application.abstract_part.AbstractController;
import com.example.demo.application.target_part.common.exceptionHandler.response.ErrorResponse;
import com.example.demo.application.target_part.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Operations with descriptions provided specifically for this controller")
public class PersonController extends AbstractController<Person, PersonDto, PersonService, PersonMapper> {

    public PersonController(PersonService service, PersonMapper mapper) {
        super(service, mapper);
    }

    @Override
    @Operation(description = "Get all people", summary = "Get all people", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<PersonDto>> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(description = "Get person by id", summary = "Get person by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PersonDto> get(Long id) {
        return super.get(id);
    }

    @Override
    @Operation(description = "Save new person", summary = "Save new person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PersonDto> save(PersonDto dto) {
        return super.save(dto);
    }

    @Override
    @Operation(description = "Save all new people", summary = "Save all new people", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<PersonDto>> saveAll(List<PersonDto> dtos) {
        return super.saveAll(dtos);
    }

    @Override
    @Operation(description = "Update person", summary = "Update person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PersonDto> update(PersonDto dto) {
        return super.update(dto);
    }

    @Override
    @Operation(description = "Delete person", summary = "Delete person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> delete(Long id) {
        return super.delete(id);
    }

    @Override
    @Operation(description = "Delete all people", summary = "Delete all people", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> deleteAll() {
        return super.deleteAll();
    }

    @Operation(description = "Add some projects to person", summary = "Add some projects to person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{personId}/projects")
    public ResponseEntity<PersonDto> addProjectsToPerson(@PathVariable Long personId, @RequestBody Set<Long> projectIds) {
        return ResponseEntity.ok(super.service.addProjectsToPerson(personId, projectIds));
    }

    @Operation(description = "Remove some projects from person", summary = "Remove some projects from person", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{personId}/projects")
    public ResponseEntity<PersonDto> removeProjectsFromPerson(@PathVariable Long personId, @RequestBody Set<Long> projectIds) {
        return ResponseEntity.ok(super.service.removeProjectsFromPerson(personId, projectIds));
    }
}
