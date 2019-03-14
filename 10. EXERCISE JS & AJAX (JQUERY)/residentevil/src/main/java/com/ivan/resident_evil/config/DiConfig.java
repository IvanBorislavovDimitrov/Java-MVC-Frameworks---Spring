package com.ivan.resident_evil.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
