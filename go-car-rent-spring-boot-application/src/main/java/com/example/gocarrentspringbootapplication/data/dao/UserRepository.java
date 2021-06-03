package com.example.gocarrentspringbootapplication.data.dao;

import com.example.gocarrentspringbootapplication.data.po.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByEmailAndPassword(String email, String password);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByIdAndPassword(Long id, String password);
}
