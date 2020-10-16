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
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
        }
        User user = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user = userDAO.getUser(email);
        if (email.isEmpty()) {
            request.setAttribute("errorLogin", "Email is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (password.isEmpty()) {
            request.setAttribute("errorPassword", "Password is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errorLogin", "Email is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (!userDAO.checkEmail(email)) {
            request.setAttribute("errorLogin", "Email is not register");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (!password.matches(".{4,}")) {
            request.setAttribute("errorPassword", "Password is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (!user.getPassword().equals(Hasher.getHash(password, "MD5")) && password.length() >= 4) {
            request.setAttribute("errorPassword", "Password is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2 * 60 * 60);
        session.setAttribute("user", user);
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
