package com.example.gocarrentspringbootapplication.api.dao.repositories;

import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDetailsRepository
        extends JpaRepository<AnnouncementDetails, Long>, JpaSpecificationExecutor<AnnouncementDetails> {
}
