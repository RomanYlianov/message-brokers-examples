package com.example.kafkadocker.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic createTopic()
    {
        return TopicBuilder.name("channel1").partitions(1).replicas(1).build();
    }
}
