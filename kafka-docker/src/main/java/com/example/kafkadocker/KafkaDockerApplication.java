package com.example.kafkadocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.AcknowledgeMode;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;

@EnableKafka
@SpringBootApplication
public class KafkaDockerApplication {


	@KafkaListener(topics = "channel1")
	public void receive(String message, MessageHeaders headers/*, Acknowledgment ack*/)
	{
		System.out.println("receive "+message);
		//ack.acknowledge();
	}


	public static void main(String[] args) {
		SpringApplication.run(KafkaDockerApplication.class, args);
	}

}
