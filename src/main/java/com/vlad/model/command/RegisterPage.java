package com.vlad.model.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterPage implements Command{
    private final String URL = "/registerPage";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./WEB-INF/jsp/Register.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}