package com.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.rabbitmq.model.User;


@Service
public class RabbitMQJsonConsumer {
	
	private Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	@RabbitListener(queues = {"${spring.rabbitmq.json.queue.name}"})
	public void consume(User user) {
		LOGGER.info(String.format("Json Message Consumed: %s", user.toString()));
	}

}
