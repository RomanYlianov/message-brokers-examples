package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.model.SensorData;
import com.example.rabbitmqdemo.service.RabbitMqJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class TestController {


    private RabbitMqJsonProducer producer;

    @Autowired
    public TestController(RabbitMqJsonProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public void send(@RequestBody SensorData data)
    {
        producer.send(data);
    }

}
