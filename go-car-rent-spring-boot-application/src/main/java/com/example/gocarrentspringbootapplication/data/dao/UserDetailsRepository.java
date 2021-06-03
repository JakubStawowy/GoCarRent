package com.example.gocarrentspringbootapplication.data.dao;

import com.example.gocarrentspringbootapplication.data.po.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
}
