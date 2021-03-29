package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> getAllBySenderAndReceiver(Long senderId, Long receiverId);
}
