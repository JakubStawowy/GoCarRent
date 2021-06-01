package com.example.gocarrentspringbootapplication.amqp.services;

import com.example.gocarrentspringbootapplication.amqp.api.IQueueService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public final class RabbitMessageQueueService implements IQueueService {

    private static final String host = "localhost";

    @Override
    public void putNewQueue(final String queueName) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropQueue(final String queueName) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDelete(queueName);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
