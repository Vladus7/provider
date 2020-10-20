package com.vlad.controller.filter;

import com.vlad.model.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/service","/account","/buy","/download","/refill","/tariff","/delete_tariff","/delete_service","/create_tariff", "/create_service","/create_account","/change_tariff","/change_service","/banned","/accountList"})
public class LoginFilter implements Filter {
    /**
     * filter is user login
     *
     * redirect on login page
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpSession session = httpReq.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            session.invalidate();
            httpResponse.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
