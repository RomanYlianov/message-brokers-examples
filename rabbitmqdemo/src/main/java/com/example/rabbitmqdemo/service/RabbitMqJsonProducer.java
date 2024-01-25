package com.example.rabbitmqdemo.service;

import com.example.rabbitmqdemo.model.SensorData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.routing-key}")
    private String routingKey;

    private RabbitTemplate template;

    @Autowired
    public RabbitMqJsonProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void send(SensorData dto)
    {
        template.convertAndSend(exchange, routingKey, dto, m->{
            m.getMessageProperties().setMessageId("mid");
            m.getMessageProperties().getHeaders().put("group", "gr1");
            return m;
        });
    }

}
