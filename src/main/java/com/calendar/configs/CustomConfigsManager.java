package com.calendar.configs;


import com.calendar.components.SimpleDateFormatter;
import com.calendar.components.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfigsManager {

    @Bean
    public DateFormatter dateFormatter(){
        return new SimpleDateFormatter();
    }
}
