package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
}
