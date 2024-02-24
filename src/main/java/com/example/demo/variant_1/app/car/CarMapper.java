package com.example.demo.variant_1.app.car;

import com.example.demo.variant_1.abstract_crud.mapper.CrudMapper;
import com.example.demo.variant_1.app.user.UserMapper;
import com.example.demo.variant_1.app.user.UserRepository;
import org.mapstruct.Mapper;

@Mapper(config = CrudMapper.class, uses = {UserRepository.class, UserMapper.class})
public abstract class CarMapper implements CrudMapper<Car, CarRequest, CarResponse> {
}
