package com.calendar.configs;


import com.calendar.components.SimpleDateFormatter;
import com.calendar.interfacies.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomeConfigsManager {

    @Bean
    public DateFormatter dateParser(){
        return new SimpleDateFormatter();
    }
}
