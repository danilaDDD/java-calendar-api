package com.calendar.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	@Value("${spring.application.name}")
	private String name;

	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot! " + name;
	}

}
