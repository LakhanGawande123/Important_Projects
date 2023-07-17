package com.example.demo.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateBeanConfig {

	@Bean
	RestTemplate getTemplate() {

		return new RestTemplate();
	}

}