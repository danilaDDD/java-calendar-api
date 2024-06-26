package com.calendar.configs;

import com.calendar.filters.CheckApiClientTokenFilter;
import com.calendar.filters.CheckUserTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {
    @Bean
    public FilterRegistrationBean<CheckApiClientTokenFilter> filterByApiClientBean(CheckApiClientTokenFilter checkApiClientTokenFilter) {
        FilterRegistrationBean<CheckApiClientTokenFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(checkApiClientTokenFilter);
        registrationBean.addUrlPatterns("/users/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CheckUserTokenFilter> filterByUserBean(CheckUserTokenFilter checkUserTokenFilter){
        FilterRegistrationBean<CheckUserTokenFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(checkUserTokenFilter);
        registrationBean.addUrlPatterns("/event-groups/*");
        registrationBean.addUrlPatterns("/events/*");

        return registrationBean;
    }
}
