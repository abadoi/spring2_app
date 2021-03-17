package com.andrei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DemoApplication {

//	private final MyConversionService conversionService;
//
//	@Override
//	public void run(final String... args) {
//		userService
//				.getUserByIdAsync("1")
//				.subscribe(user -> log.info("Get user async: {}", user));
//	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

//		Client client = new Client();
//		System.out.println(client.getResult());
	}

}
