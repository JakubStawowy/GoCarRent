package com.example.gocarrentspringbootapplication.impl.dao.repositories;

import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnouncementRepository extends
        JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
}
