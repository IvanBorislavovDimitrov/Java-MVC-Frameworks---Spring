package com.ivan.filters;

import com.ivan.constants.SessionConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NotLoggedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession();
        if (httpSession.getAttribute(SessionConstants.LOGGED_USER) == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
