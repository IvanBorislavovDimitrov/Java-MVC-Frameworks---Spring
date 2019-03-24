package com.ivan.resident_evil.filter;

import com.ivan.resident_evil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersDI {

    private final UserService userService;

    @Autowired
    public FiltersDI(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public FilterRegistrationBean<HasRoleAdminFilter> hasRoleFilterFilterRegistrationBean() {
        FilterRegistrationBean<HasRoleAdminFilter> hasRoleFilterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        hasRoleFilterFilterRegistrationBean.setFilter(new HasRoleAdminFilter(userService));
        hasRoleFilterFilterRegistrationBean.addUrlPatterns("/admin/*");
        hasRoleFilterFilterRegistrationBean.addUrlPatterns("/admin");

        return hasRoleFilterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<HasRoleModeratorFilter> hasRoleModeratorFilterFilterRegistrationBean() {
        FilterRegistrationBean<HasRoleModeratorFilter> hasRoleModeratorFilterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        hasRoleModeratorFilterFilterRegistrationBean.setFilter(new HasRoleModeratorFilter(userService));
        hasRoleModeratorFilterFilterRegistrationBean.addUrlPatterns("/moderator/*");
        hasRoleModeratorFilterFilterRegistrationBean.addUrlPatterns("/moderator");

        return hasRoleModeratorFilterFilterRegistrationBean;
    }
}
