package com.example.gocarrentspringbootapplication.amqp.dao;

import com.example.gocarrentspringbootapplication.amqp.po.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE receiver_id=?1 AND archived=?2", nativeQuery = true)
    List<Message> getAllByReceiverAndArchived(Long userId, Boolean archived);
}
