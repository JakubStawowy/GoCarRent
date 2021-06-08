package com.example.gocarrentspringbootapplication.amqp.dao;

import com.example.gocarrentspringbootapplication.amqp.po.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    String GET_ALL_BY_RECEIVER_AND_ARCHIVED_QUERY = "SELECT * FROM messages WHERE receiver_id=?1 AND archived=?2 ORDER BY sent_at DESC";
    @Query(value = GET_ALL_BY_RECEIVER_AND_ARCHIVED_QUERY, nativeQuery = true)
    List<Message> getAllByReceiverAndArchived(Long userId, Boolean archived);
}
