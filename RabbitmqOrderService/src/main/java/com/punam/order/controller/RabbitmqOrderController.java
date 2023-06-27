package com.punam.order.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.punam.domains.model.Order;
import com.punam.domains.model.OrderEvent;
import com.punam.order.producer.RabbitmqOrderProducer;



@RestController
@RequestMapping("/api/v1")
public class RabbitmqOrderController {
	
	@Autowired
	private RabbitmqOrderProducer producer;
	
	
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Order order) {
		
		order.setOrderId(UUID.randomUUID().toString());
		
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("Order status is in pending");
		orderEvent.setOrder(order);
		
		producer.sendMessage(orderEvent);
		
		return "Order Placed Successfully..!";
		
	}

}
