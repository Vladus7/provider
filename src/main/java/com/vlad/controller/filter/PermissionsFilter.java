package com.vlad.controller.filter;

import com.vlad.model.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter({"/service","/tariff"})
public class PermissionsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
//        HttpSession session = httpReq.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user!=null){
//            if(user.getBill()==0){
//                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//                httpResponse.sendRedirect("/refill");
//                return;
//            }
//
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("<====================>");
    }
}
