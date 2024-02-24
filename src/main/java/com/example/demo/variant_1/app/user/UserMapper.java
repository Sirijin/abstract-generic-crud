package com.example.demo.variant_1.app.user;

import com.example.demo.variant_1.abstract_crud.mapper.CrudMapper;
import com.example.demo.variant_1.app.car.CarMapper;
import com.example.demo.variant_1.app.car.CarRepository;
import org.mapstruct.Mapper;

@Mapper(config = CrudMapper.class, uses = {CarRepository.class, CarMapper.class})
public abstract class UserMapper implements CrudMapper<User, UserRequest, UserResponse> {

    public abstract UserDto toUserDto(User user);
}
