package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootMicroservices2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservices2Application.class, args);
	}

	@GetMapping("/micro-2")
	public String m1() {
		return "Microservice-2.....!";
	}

}
