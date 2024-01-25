package com.example.kafkademo;

import com.example.kafkademo.model.SensorData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;

@EnableKafka
@SpringBootApplication
public class KafkademoApplication {

	@KafkaListener(topics = "mytopic", topicPartitions = {@TopicPartition(topic = "mytopic", partitions = { "1" })})
	public void receiveMessage(ConsumerRecord<String, SensorData> record, MessageHeaders headers)
	{
		String groupId = headers.get(KafkaHeaders.GROUP_ID).toString();
		System.out.println("from group 1 "+groupId);
		System.out.println(record.partition());
		System.out.println(record.key());
		System.out.println(record.value());
	}

	//id and groupId is not working
	@KafkaListener(topics = "mytopic", topicPartitions = {@TopicPartition(topic = "mytopic", partitions = { "0" })})
	public void receiveMessage1(ConsumerRecord<String, SensorData> record, MessageHeaders headers)
	{
		String groupId = headers.get(KafkaHeaders.GROUP_ID).toString();

		System.out.println("from group 2 "+groupId);
		System.out.println(record.partition());
		System.out.println(record.key());
		System.out.println(record.value());
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
	}

}
