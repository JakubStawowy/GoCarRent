package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByEmailAndPassword(String email, String password);
}
