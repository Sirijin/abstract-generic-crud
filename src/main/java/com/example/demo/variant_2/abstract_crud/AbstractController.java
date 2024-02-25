package com.example.demo.variant_2.abstract_crud;

import com.example.demo.variant_2.app.common.ErrorType;
import com.example.demo.variant_2.app.common.exceptionHandler.response.ErrorResponse;
import com.example.demo.variant_2.app.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractController<E extends AbstractEntity, D extends Dto, S extends CommonService<E, D>, M extends CommonMapper<E, D>> implements CommonController<D> {

    protected final S service;
    protected final M mapper;

    @Override
    @Operation(description = "Save one", summary = "Save one", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Dto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<D> save(@RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        E savedEntity = service.save(entity).orElseThrow(() -> new RuntimeException(
                String.format(ErrorType.ENTITY_NOT_SAVED.getDescription(), entity.toString())
        ));
        return ResponseEntity.ok(mapper.toDto(savedEntity));
    }

    @Override
    @Operation(description = "Save all", summary = "Save all", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<D>> saveAll(@RequestBody List<D> dtos) {
        return ResponseEntity.ok(service.saveAll(mapper.toEntities(dtos)));
    }

    @Override
    @Operation(description = "Update one by id", summary = "Update one by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Dto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<D> update(@RequestBody D dto) {
        E updatedEntity = service.get(mapper.toEntity(dto).getId())
                .map(entity -> {
                    mapper.updateEntity(dto, entity);
                    return service.save(entity).orElseThrow(() -> new RuntimeException(
                            String.format(ErrorType.ENTITY_NOT_UPDATED.getDescription(), entity)
                    ));
                }).orElseThrow(() -> new RuntimeException(
                        String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), dto)
                ));
        return ResponseEntity.ok(mapper.toDto(updatedEntity));
    }

    @Override
    @Operation(description = "Get one by id", summary = "Get one by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Dto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<D> get(@RequestParam Long id) {
        E entity = service.get(id).orElseThrow(() -> new RuntimeException(
                String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)
        ));
        return ResponseEntity.ok(mapper.toDto(entity));
    }

    @Override
    @Operation(description = "Get all", summary = "Get all", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<D>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @Operation(description = "Delete one by id", summary = "Delete one by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @Override
    @Operation(description = "Delete all", summary = "Delete all", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(service.deleteAll());
    }
}