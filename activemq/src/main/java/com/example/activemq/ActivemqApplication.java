package com.example.activemq;

import com.example.activemq.model.SensorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;

@SpringBootApplication
public class ActivemqApplication {


	@Value("${activemq.queue.name}")
	private String queueName;
/*
	@JmsListener(destination = "myqueue")
	public void receiveMessage(SensorData dto, MessageHeaders headers)
	{
		String groupId = headers.get("JMSXGroupID").toString();
		System.out.println("group is "+groupId);
		System.out.println("id "+dto.getId());
		System.out.println("receive "+dto.toString());
	}
*/
	@JmsListener(destination = "myqueue", id = "mid")
	public void receiveMessage1(SensorData dto, @Header(name = "JMSXGroupID") String groupId)
	{
		System.out.println("messages with id mid");
		System.out.println("group id "+groupId);
		System.out.println("id "+dto.getId());
		System.out.println("receive "+dto.toString());
	}

	@JmsListener(destination = "myqueue", id = "mid1")
	public void receiveMessage2(SensorData dto, @Header(name = "JMSXGroupID") String groupId)
	{
		System.out.println("messages with id mid1");
		System.out.println("group id "+groupId);
		System.out.println("id "+dto.getId());
		System.out.println("receive "+dto.toString());
	}


	public static void main(String[] args) {
		SpringApplication.run(ActivemqApplication.class, args);
	}

}
