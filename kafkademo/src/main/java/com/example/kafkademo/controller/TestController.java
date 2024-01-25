package com.example.kafkademo.controller;


import com.example.kafkademo.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;


import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/kafka")
public class TestController {


    private KafkaTemplate<String, SensorData> kafkaTemplate;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupName;

    @Autowired
    public TestController(KafkaTemplate<String, SensorData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void send(@RequestBody SensorData data)
    {
        CompletableFuture<SendResult<String, SensorData>> future =  kafkaTemplate.send("mytopic", "mkey1", data);
        future.whenComplete((rs,ex)->System.out.println(future+": "+rs.getRecordMetadata()));
        kafkaTemplate.flush();
    }

    @PostMapping("/1")
    public void send1(@RequestBody SensorData data)
    {
        Map map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC, "mytopic");
        map.put(KafkaHeaders.GROUP_ID, groupName);

        map.put(KafkaHeaders.KEY,  UUID.randomUUID().toString());
        map.put(KafkaHeaders.PARTITION, 0);
        GenericMessage message = new GenericMessage(data, new MessageHeaders(map));
        kafkaTemplate.send(message);
        kafkaTemplate.flush();
    }

}
