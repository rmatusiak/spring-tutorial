package com.acme.order;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data

public class Customer {

	private final String name;

	private final String email;

	private final String address;

}
