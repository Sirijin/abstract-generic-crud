package com.example.demo.app.car;

import com.example.demo.abstract_crud.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class CarService extends AbstractCrudService<Car, Long, CarRequest, CarResponse> {

    public CarService(CarRepository repository, CarMapper mapper) {
        super(repository, mapper);
    }

    public void test2() {
        System.out.println("To implement custom non-CRUD logic");
    }
}
