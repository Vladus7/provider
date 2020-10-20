package com.vlad.controller.filter;

import com.vlad.model.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/delete_tariff","/delete_service","/create_tariff", "/create_service","/create_account","/change_tariff","/change_service","/banned","/accountList"})
public class PermissionsFilter implements Filter {
    /**
     * filter user permission
     *
     * redirect on login page
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpSession session = httpReq.getSession();
        User user = (User) session.getAttribute("user");
        if ("user".equals(user.getPermissions())){
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                session.invalidate();
                httpResponse.sendRedirect("/login");
                return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
