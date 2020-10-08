package com.vlad.model.command;

import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command{
    private final String URL = "/loginPage";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
