package com.vlad.controller;

import com.vlad.model.command.Command;
import com.vlad.model.command.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/")
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = getCommands(request);
        try {
            command.process(request, response);
        } catch (NullPointerException e){
            UnknownCommand unknownCommand = new UnknownCommand();
            unknownCommand.process(request,response);
        }
    }

    private Command getCommands(HttpServletRequest request) {
        Map<String, Command> commandHashMap = (Map<String, Command>) getServletContext().getAttribute("commandHashMap");
        String[] array = request.getServletPath().split("/");
        return commandHashMap.get("/" + array[1]);
    }
}
