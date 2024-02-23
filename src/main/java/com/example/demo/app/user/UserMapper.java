package com.example.demo.app.user;

import com.example.demo.abstract_crud.CrudMapper;
import com.example.demo.app.car.CarMapper;
import com.example.demo.app.car.CarRepository;
import org.mapstruct.Mapper;

@Mapper(config = CrudMapper.class, uses = {CarRepository.class, CarMapper.class})
public abstract class UserMapper implements CrudMapper<User, UserRequest, UserResponse> {

    public abstract UserDto toUserDto(User user);
}
