package com.example.demo.app.car;

import com.example.demo.abstract_crud.AbstractCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController extends AbstractCrudController<Car, Long, CarRequest, CarResponse> {

    private final CarService carService;

    public CarController(CarService carService) {
        super(carService);
        this.carService = carService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<CarResponse>> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> getOne(@PathVariable("id") Long id) {
        return super.getOne(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<CarResponse> create(@Validated @RequestBody CarRequest request) {
        return super.create(request);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> update(@PathVariable("id") Long id, @Validated @RequestBody CarRequest request) {
        return super.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
