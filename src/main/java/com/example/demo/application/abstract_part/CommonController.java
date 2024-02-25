package com.example.demo.application.abstract_part;

import com.example.demo.application.target_part.common.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<D extends Dto> {

    @GetMapping("/all")
    ResponseEntity<BaseResponse<D>> getAll();

    @GetMapping
    ResponseEntity<D> get(@RequestParam Long id);

    @PostMapping
    ResponseEntity<D> save(@RequestBody D dto);

    @PostMapping("/all")
    ResponseEntity<BaseResponse<D>> saveAll(@RequestBody List<D> dtos);

    @PutMapping
    ResponseEntity<D> update(@RequestBody D dto);

    @DeleteMapping
    ResponseEntity<Boolean> delete(@RequestParam Long id);

    @DeleteMapping("/all")
    ResponseEntity<Boolean> deleteAll();
}