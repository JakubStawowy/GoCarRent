package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
}
