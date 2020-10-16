package com.vlad.model.command;

import com.vlad.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnknownCommand implements Command {
    private final String URL = "/error";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./WEB-INF/error/error404.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
