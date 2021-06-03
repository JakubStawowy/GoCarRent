package com.example.gocarrentspringbootapplication.amqp.api;

import com.example.gocarrentspringbootapplication.amqp.po.Message;

public interface IMessageService {
    void sendMessage(Message message);
}
