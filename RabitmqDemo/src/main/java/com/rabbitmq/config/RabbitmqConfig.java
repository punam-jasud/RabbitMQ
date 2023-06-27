package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${spring.rabbitmq.queue.name}")
	private String queueName;
	
	@Value("${spring.rabbitmq.routingkey.name}")
	private String routingKey;
	
	@Value("${spring.rabbitmq.json.queue.name}")
	private String jsonQueueName;
	
	@Value("${spring.rabbitmq.json.routingkey.name}")
	private String jsonRoutingKey;
	
	
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueueName);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue())
				.to(exchange())
				.with(jsonRoutingKey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	} 
	
	
	
	//Following beans are auto configured by spring boot amqp
	//RabbitmqTemplate
	//RabbitmqAdmin
	//ConnectionFactory
}
