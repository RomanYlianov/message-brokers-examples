package com.example.ibmmqdemoboot;

import com.example.ibmmqdemoboot.model.JSONSensorData;
import com.example.ibmmqdemoboot.model.SensorData;
import com.example.ibmmqdemoboot.model.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;

@EnableJms
@SpringBootApplication
public class IbmmqdemobootApplication {


	/*@JmsListener(destination = "DEV.QUEUE.1")
	public void receive(SensorData message)
	{
		System.out.println("receive "+message.toString());
	}*/

	@JmsListener(destination = "DEV.QUEUE.2")
	public void receive(TestModel model)
	{
		System.out.println("received ");
		System.out.println(model.toString());
	}

	@JmsListener(destination = "${app.queue.name}", id = "mid")
	public void receive(String message, MessageHeaders headers)
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			String groupId = headers.get("JMSXGroupID").toString();
			System.out.println("group id "+ groupId+", message id mid");
			JSONSensorData data = mapper.readValue(message, JSONSensorData.class);
			System.out.println("receive "+message.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@JmsListener(destination = "${app.queue.name}", id = "mid1")
	public void receive1(String message , MessageHeaders headers)
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			String groupId = headers.get("JMSXGroupID").toString();
			System.out.println("group id "+ groupId+", message id mid1");
			JSONSensorData data = mapper.readValue(message, JSONSensorData.class);
			System.out.println("receive "+message.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IbmmqdemobootApplication.class, args);
	}

}
