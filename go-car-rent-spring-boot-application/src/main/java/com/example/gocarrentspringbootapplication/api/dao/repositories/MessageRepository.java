package com.example.gocarrentspringbootapplication.api.dao.repositories;

import com.example.gocarrentspringbootapplication.impl.models.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE receiver_id=?1", nativeQuery = true)
    List<Message> getAllByReceiver(Long userId);
}
