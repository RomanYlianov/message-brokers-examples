package com.example.activemq.controller;

import com.example.activemq.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/activemq")
public class TestController {

    @Value("${activemq.queue.name}")
    private String queueName;

    private JmsTemplate template;



    public TestController(JmsTemplate template) {
        this.template = template;
    }

    @PostMapping
    public String send(@RequestBody SensorData dto)
    {

        template.convertAndSend(queueName, dto, m -> {
            m.setJMSMessageID("mid1");
            m.setStringProperty("JMSXGroupID", "gr1");
            return m;
        });
        return "message send";
    }

}
