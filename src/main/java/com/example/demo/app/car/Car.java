package com.example.demo.app.car;

import com.example.demo.abstract_crud.marker.IdentifiableEntity;
import com.example.demo.app.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car implements IdentifiableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;
}
