package com.example.demo.app.car;

import com.example.demo.abstract_crud.CrudMapper;
import com.example.demo.app.user.UserMapper;
import com.example.demo.app.user.UserRepository;
import org.mapstruct.Mapper;

@Mapper(config = CrudMapper.class, uses = {UserRepository.class, UserMapper.class})
public abstract class CarMapper implements CrudMapper<Car, CarRequest, CarResponse> {
}
