package com.example.kafkadocker.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev1")
public class Producer {

    public KafkaTemplate<String, String> template;

    @Value("${app.kafka.key}")
    private String key;

    @Value("${app.kafka.topic}")
    private String topic;


    public Producer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping
    public void send(String message)
    {
        this.template.send(topic, key, message);
    }

}
