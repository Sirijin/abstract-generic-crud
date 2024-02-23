package com.example.demo.app.user;

import com.example.demo.abstract_crud.repository.JpaRepo;

public interface UserRepository extends JpaRepo<User, Long> {
}
