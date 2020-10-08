package com.vlad.model.command;

import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RefillCommand implements Command {
    private final String URL = "/refill";
    private UserDAO userDAO;

    public RefillCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if(Double.parseDouble(request.getParameter("amount"))<=5
                   // || request.getParameter("cvc").length()!=3
                   // ||request.getParameter("date")!=null
                    //||request.getParameter("cardName").length()!=16
            ){request.getRequestDispatcher("/refillPage").forward(request, response);
                return;}
        }catch (NumberFormatException e){
            request.getRequestDispatcher("/refillPage").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setBill(Double.parseDouble(request.getParameter("amount")));
        userDAO.setUser(user);
        response.sendRedirect("/tariff");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
