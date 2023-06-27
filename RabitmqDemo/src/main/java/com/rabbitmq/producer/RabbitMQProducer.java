package com.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducer {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;
	
	
	@Value("${spring.rabbitmq.routingkey.name}")
	private String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	

	public void sendMessage(String message) {
		LOGGER.info(String.format("Message Published : %s", message));
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
		
	}

}
