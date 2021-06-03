package com.example.gocarrentspringbootapplication.amqp.api;

public interface IQueueService {
    void putNewQueue(final String queueName);
    void dropQueue(final String queueName);
}
