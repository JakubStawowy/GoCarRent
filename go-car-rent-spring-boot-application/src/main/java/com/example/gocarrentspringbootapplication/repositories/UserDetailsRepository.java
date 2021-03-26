package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
}
