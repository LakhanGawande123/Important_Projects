package com.example.demo.post.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
  
@Configuration
public class AddressConfig {
  
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
