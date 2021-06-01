package com.example.gocarrentspringbootapplication.api.services;

import com.example.gocarrentspringbootapplication.impl.models.Message;

public interface IMessageService {
    void sendMessage(Message message);
}
