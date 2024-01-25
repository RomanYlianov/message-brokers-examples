package com.example.rabbitmqdemo.config;





import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

   @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.routing-key}")
    private String routingKey;

    @Bean
    public Queue jsonQueue()
    {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding jsonBinding()
    {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingKey);
    }

    @Bean
    public MessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory factory)
    {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter());
        return template;
    }

}
