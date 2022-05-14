package com.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application{
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
	}
	
}
