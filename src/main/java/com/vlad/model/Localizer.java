package com.vlad.model;


import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;


public class Localizer {

    private Localizer() {
    }

    /**
     *
     * @param request, value
     *            get value from properties.
     * @return value.
     */
    public static String getProper(HttpServletRequest request, String value) {
        String lang = "en";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lang")) {
                lang = cookie.getValue();
            }
        }
        Locale current = new Locale(lang);
        ResourceBundle rb = ResourceBundle.getBundle("messages", current);
        return rb.getString(value);
    }
}
