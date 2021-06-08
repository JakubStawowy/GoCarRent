package com.example.gocarrentspringbootapplication.data.dao;

import com.example.gocarrentspringbootapplication.data.po.AnnouncementDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementDetailsRepository
        extends JpaRepository<AnnouncementDetails, Long>, JpaSpecificationExecutor<AnnouncementDetails> {
    String FIND_ALL_QUERY = "SELECT * FROM announcement_details WHERE rent_status != 'BLOCKED' ORDER BY created_at DESC";
    @NotNull
    @Query(value = FIND_ALL_QUERY, nativeQuery = true)
    List<AnnouncementDetails> findAll();
}
