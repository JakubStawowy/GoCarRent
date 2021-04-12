package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByEmailAndPassword(String email, String password);
    Optional<User> getUserByEmail(String email);

}
