package com.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rabbitmq.model.User;


@Service
public class RabbitMQJsonProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;
	
	
	@Value("${spring.rabbitmq.json.routingkey.name}")
	private String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public void sendMessage(User user) {
		
		LOGGER.info(String.format("Message Published : %s", user.toString()));
		rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
	
	}

}
