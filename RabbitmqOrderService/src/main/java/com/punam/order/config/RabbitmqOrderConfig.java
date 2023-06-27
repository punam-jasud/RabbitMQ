package com.punam.order.config;

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
public class RabbitmqOrderConfig {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${rabbitmq.email.queue.name}")
	private String emailQueue;
	
	@Value("${rabbitmq.stock.queue.name}")
	private String stockQueue;
	
	@Value("${rabbitmq.email.routingkey.name}")
	private String emailRoutingKey;
	
	@Value("${rabbitmq.stock.routingkey.name}")
	private String stockRoutingKey;
	
	
	@Bean
	public Queue emailQueue() {
		return new Queue(emailQueue);
	}
	
	@Bean
	public Queue stockQueue() {
		return new Queue(stockQueue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Binding emailBinding() {
		return BindingBuilder.bind(emailQueue())
				.to(exchange())
				.with(emailRoutingKey);
	}
	
	@Bean
	public Binding stockBinding() {
		return BindingBuilder.bind(stockQueue())
				.to(exchange())
				.with(stockRoutingKey);
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
