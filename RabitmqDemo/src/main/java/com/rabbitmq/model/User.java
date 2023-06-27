package com.rabbitmq.model;

import lombok.Data;

@Data
public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;

}
