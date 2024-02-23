package com.example.demo.app.user;

import com.example.demo.abstract_crud.marker.IdentifiableEntity;
import com.example.demo.app.car.Car;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user", schema = "public")
public class User implements IdentifiableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "user")
    private List<Car> cars;
}

