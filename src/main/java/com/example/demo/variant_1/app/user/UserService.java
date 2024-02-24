package com.example.demo.variant_1.app.user;

import com.example.demo.variant_1.abstract_crud.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<User, Long, UserRequest, UserResponse> {

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    public void test2() {
        System.out.println("To implement custom non-CRUD logic");
    }
}
