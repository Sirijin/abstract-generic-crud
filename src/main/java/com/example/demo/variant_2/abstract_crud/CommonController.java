package com.example.demo.variant_2.abstract_crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<D extends Dto> {

    @PostMapping
    ResponseEntity<D> save(@RequestBody D dto);

    @PostMapping("/all")
    ResponseEntity<List<D>> saveAll(@RequestBody List<D> dtos);

    @PutMapping
    ResponseEntity<D> update(@RequestBody D dto);

    @GetMapping
    ResponseEntity<D> get(@RequestParam Long id);

    @GetMapping("/all")
    ResponseEntity<List<D>> getAll();

    @DeleteMapping
    Boolean delete(@RequestParam Long id);

    @DeleteMapping("/all")
    Boolean deleteAll();
}