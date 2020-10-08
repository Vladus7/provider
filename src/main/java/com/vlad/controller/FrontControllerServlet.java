package com.vlad.controller;

import com.vlad.model.command.Command;
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
//        if (request != null) {
//            String url = request.getRequestURL().toString();
//            System.out.println(request.getServletPath());
//            String queryString = request.getQueryString();
//            System.out.println(url);
//            System.out.println(queryString);
//        }
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommands(request);
        command.process(request,response);
        //       Command command = getCommands(req);
//        command.process(req,resp);
//        resp.setContentType("text/html");
//        UserDAO dbManager = new UserDAO();
//        User user = null;
//        user = dbManager.getUser(req.getParameter("email"));
//
//        System.out.println(user.getLogin()+" "+ user.getBill());
//        req.getSession().setAttribute("User", user);
//
//        List<Tariff> tariffs = dbManager.getAllTariff();
//        req.setAttribute("tariffs", tariffs);
//
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
//        resp.sendRedirect();
//        requestDispatcher.forward(req,resp);
    }

    private Command getCommands(HttpServletRequest request) {
            Map<String, Command> commandHashMap = (Map<String, Command>) getServletContext().getAttribute("commandHashMap");
            String[] array = request.getServletPath().split("/");
        //System.out.println(Arrays.toString(array));
        return commandHashMap.get("/"+array[1]);
    }
}
