package com.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
	
	private Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
	@RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
	public void consume(String message) {
		LOGGER.info(String.format("Message Consumed: %s", message));
	}

}
