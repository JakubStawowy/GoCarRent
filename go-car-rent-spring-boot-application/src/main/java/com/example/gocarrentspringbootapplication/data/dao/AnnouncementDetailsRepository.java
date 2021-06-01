package com.example.gocarrentspringbootapplication.data.dao;

import com.example.gocarrentspringbootapplication.data.po.AnnouncementDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDetailsRepository
        extends JpaRepository<AnnouncementDetails, Long>, JpaSpecificationExecutor<AnnouncementDetails> {
}
