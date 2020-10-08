package com.vlad.model.command;

import com.vlad.model.Hasher;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private final String URL = "/login";
    private UserDAO userDAO;

    public LoginCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = null;
        try {
            user = userDAO.getUser(request.getParameter("email"));
            String password = request.getParameter("password");
            if(!user.getPassword().equals(Hasher.getHash(password, "MD5"))&&password.length()>=4){
                request.setAttribute("errorPassword","Password is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            }
            //System.out.println(user.getLogin()+" "+ user.getBill());
        }catch (NullPointerException e){
            request.setAttribute("errorLogin","Email is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
