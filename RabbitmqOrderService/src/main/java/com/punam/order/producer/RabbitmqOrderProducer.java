package com.punam.order.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.punam.domains.model.OrderEvent;


@Service
public class RabbitmqOrderProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqOrderProducer.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${rabbitmq.email.routingkey.name}")
	private String emailRoutingKey;
	
	@Value("${rabbitmq.stock.routingkey.name}")
	private String stockRoutingKey;
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public void sendMessage(OrderEvent orderEvent) {
		
		LOGGER.info(String.format("Message Published to RabbitMQ : %s", orderEvent.toString()));
		rabbitTemplate.convertAndSend(exchangeName, emailRoutingKey, orderEvent);
		rabbitTemplate.convertAndSend(exchangeName, stockRoutingKey, orderEvent);

	}

}
