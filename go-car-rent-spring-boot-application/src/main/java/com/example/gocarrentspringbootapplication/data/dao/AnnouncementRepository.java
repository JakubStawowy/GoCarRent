package com.example.gocarrentspringbootapplication.data.dao;

import com.example.gocarrentspringbootapplication.data.po.Announcement;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
    String AUTHOR_ANNOUNCEMENTS_QUERY = "SELECT * FROM announcements WHERE author_id=?1";
    @Query(value = AUTHOR_ANNOUNCEMENTS_QUERY, nativeQuery = true)
    List<Announcement> getAllByAuthor(Long id);
}
