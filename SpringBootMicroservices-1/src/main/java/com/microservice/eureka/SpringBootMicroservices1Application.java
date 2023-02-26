package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootMicroservices1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservices1Application.class, args);
	}
	
	@GetMapping("/micro-1")
	public String m1() {
		return "Microservice-1.....!";
	}

}
