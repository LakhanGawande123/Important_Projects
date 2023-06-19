package com.nodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NodeService1Application {

	public static void main(String[] args) {
		SpringApplication.run(NodeService1Application.class, args);
		
		System.out.println(">>>>>>>>>> Start......");
	}
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       return builder.build();
    }

}
