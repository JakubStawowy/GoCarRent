package com.example.gocarrentspringbootapplication.impl.dao;

import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
}
