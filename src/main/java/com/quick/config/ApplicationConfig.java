package com.quick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

@Configuration
public class ApplicationConfig {

    @Bean
    public PatternsRequestCondition patternsRequestCondition() {
        return new PatternsRequestCondition();
    }
}
