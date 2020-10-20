package com.vlad.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = { "/*" })
public class CookieLocaleFilter implements Filter {

    /**
     * filter locale
     *
     * change locale
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter("locale") != null) {
            Cookie cookie = new Cookie("lang", req.getParameter("locale"));
            res.addCookie(cookie);
            res.sendRedirect(getFullURL(req));
            return;
        }

        chain.doFilter(request, response);
    }

    private static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            String url = requestURL.append('?').append(queryString).toString();
            return url.replaceAll("[&?]locale.*?(?=&|\\?|$)", "");
        }
    }
}
