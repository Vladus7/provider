package com.vlad.controller;

import com.vlad.model.AppException;
import com.vlad.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/")
public class FrontControllerServlet extends HttpServlet {
    private final String ERROR = "error";
    private final String ERROR_MESSAGES = "errorMessages";
    private final String ERROR_JSP_PATH = "./WEB-INF/error/error.jsp";
    private final String ENCODING = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding(ENCODING);
            response.setCharacterEncoding(ENCODING);
            Command command = getCommands(request);
            command.process(request, response);
        } catch (AppException exception) {
            request.setAttribute(ERROR, exception.getErrorCode());
            request.setAttribute(ERROR_MESSAGES, exception.getError());
            request.getRequestDispatcher(ERROR_JSP_PATH).forward(request, response);
        } catch (Exception e) {
            request.setAttribute(ERROR, 500);
            request.setAttribute(ERROR_MESSAGES, "Sorry :'(");
            request.getRequestDispatcher(ERROR_JSP_PATH).forward(request, response);
        }
    }

    private Command getCommands(HttpServletRequest request) throws AppException {
        Map<String, Command> commandHashMap = (Map<String, Command>) getServletContext().getAttribute("commandHashMap");
        String[] array = request.getServletPath().split("/");
        Command command = commandHashMap.get("/" + array[1]);
        if (command == null) {
            throw new AppException(array[1], 404, "Not Found");
        }
        return command;
    }
}
