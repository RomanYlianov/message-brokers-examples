package com.example.rabbitmqdemo.service;

import com.example.rabbitmqdemo.model.SensorData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"}, id = "mid")
    public void consumerJsonListener(SensorData dto, MessageHeaders headers)
    {
        System.out.println("received message with mid");
        String gName = headers.get("group").toString();
        System.out.println("group name "+gName);
        System.out.println(dto.toString());
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"}, id = "mid1")
    public void consumerJsonListener1(SensorData dto, MessageHeaders headers)
    {
        System.out.println("received message with mid1");
        String gName = headers.get("group").toString();
        System.out.println("group name "+gName);
        System.out.println(dto.toString());
    }
}
