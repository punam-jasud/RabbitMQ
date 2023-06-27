package com.punam.email.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.punam.domains.model.OrderEvent;


@Service
public class RabbitmqEmailConsumer {
	
	private Logger LOGGER = LoggerFactory.getLogger(RabbitmqEmailConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.email.queue.name}"})
	public void consume(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order Event Consumed from RabbitMQ: %s", orderEvent.toString()));
	}

}
