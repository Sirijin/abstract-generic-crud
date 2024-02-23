package com.example.demo.app.user;

import com.example.demo.abstract_crud.AbstractCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<User, Long, UserRequest, UserResponse> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserResponse>> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserResponse> getOne(@PathVariable("id") Long id) {
        return super.getOne(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest request) {
        return super.create(request);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @Validated @RequestBody UserRequest request) {
        return super.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @GetMapping("/test")
    public void test() {
        userService.test2();
    }
}
