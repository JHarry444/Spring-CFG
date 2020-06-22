package com.qa.demo.config;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean // managed object - one that's created by Spring
	public String message() {
		return "Hello, World! " + LocalTime.now();
	}
	
	@Bean // bean 2
	public String message2(String message) {
		return message + "Electric boogaloo";
	}

}
