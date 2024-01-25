package com.example.ibmmqdemoboot.controller;

import com.example.ibmmqdemoboot.model.JSONSensorData;
import com.example.ibmmqdemoboot.model.SensorData;
import com.example.ibmmqdemoboot.model.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ibmmq")
public class DemoController {


    private JmsTemplate template;

    @Autowired
    public DemoController(JmsTemplate template) {
        this.template = template;
    }

    @Value("${app.queue.name}")
    private String queueName;

    @PostMapping
    public String send(@RequestBody SensorData data)
    {
        template.convertAndSend(queueName, data);
        return "send";
    }

    @PostMapping("/1")
    public String sendJSON(@RequestBody JSONSensorData sensorData)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            String serializedSensorData = mapper.writeValueAsString(sensorData);
            System.out.println("serialized: "+serializedSensorData);
            template.convertAndSend(queueName, serializedSensorData, m->{
                m.setJMSMessageID("mid");
                m.setStringProperty("JMSXGroupID", "group 1");
                return m;
            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "send";
    }

    @PostMapping("/2")
    public String sendTest(@RequestBody TestModel model)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {


            template.convertAndSend("DEV.QUEUE.2", model);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "send";
    }

}
