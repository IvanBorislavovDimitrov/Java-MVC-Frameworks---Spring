package com.ivan.resident_evil.filter;

import com.ivan.resident_evil.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HasRoleAdminFilter implements Filter {

    private final UserService userService;

    public HasRoleAdminFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication instanceof AnonymousAuthenticationToken)) {
            ((HttpServletResponse) servletResponse).sendRedirect("/unauthorized");
            return;
        }
        String currentUserName = authentication.getName();
        if (!userService.hasRoleAdmin(currentUserName)) {
            ((HttpServletResponse) servletResponse).sendRedirect("/unauthorized");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
