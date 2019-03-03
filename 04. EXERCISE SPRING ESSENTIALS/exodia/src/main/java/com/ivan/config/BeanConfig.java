package com.ivan.config;

import com.ivan.filters.LoggedUserFilter;
import com.ivan.filters.NotLoggedUserFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<NotLoggedUserFilter> notLoggedUserFilterFilterRegistrationBean() {
        FilterRegistrationBean<NotLoggedUserFilter> notLoggedUserFilterRegistrationBean = new FilterRegistrationBean<>();
        notLoggedUserFilterRegistrationBean.setFilter(new NotLoggedUserFilter());
        notLoggedUserFilterRegistrationBean.addUrlPatterns("/home", "/schedule", "/print", "/details", "/logout");

        return notLoggedUserFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoggedUserFilter> loggedUserFilterRegistrationBean() {
        FilterRegistrationBean<LoggedUserFilter> loggedUserFilterRegistrationBean = new FilterRegistrationBean<>();
        loggedUserFilterRegistrationBean.setFilter(new LoggedUserFilter());
        loggedUserFilterRegistrationBean.addUrlPatterns("/", "/login", "/register");

        return loggedUserFilterRegistrationBean;
    }
}
