package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rabbitmq.model.User;
import com.rabbitmq.producer.RabbitMQJsonProducer;
import com.rabbitmq.producer.RabbitMQProducer;


@RestController
@RequestMapping("/api/v1")
public class RabbitMQController {
	
	@Autowired
	private RabbitMQProducer producer;
	
	@Autowired
	private RabbitMQJsonProducer jsonProducer;
	
	
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		producer.sendMessage(message);
		return ResponseEntity.ok("Message Published Successfully..");
	}
	
	@PostMapping("/json/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		jsonProducer.sendMessage(user);
		return ResponseEntity.ok("Json Message Published Successfully..");
	}
	

}
