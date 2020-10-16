package com.vlad.model.command;

import com.vlad.model.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccountCommand implements Command {
    private final String URL = "/create_account";
    private UserDAO userDAO;

    public CreateAccountCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
        }
        String email = request.getParameter("email");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String telephone = request.getParameter("telephone");
        if (email.isEmpty()) {
            request.setAttribute("errorLogin", "Email is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (password1.isEmpty()) {
            request.setAttribute("errorPassword", "Password is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (password2.isEmpty()) {
            request.setAttribute("errorPassword2", "Password is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errorLogin", "Email is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (userDAO.checkEmail(email)) {
            request.setAttribute("errorLogin", "Email used");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (!password1.matches(".{4,}")) {
            request.setAttribute("errorPassword", "Password is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (!password2.matches(".{4,}")) {
            request.setAttribute("errorPassword2", "Password is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (!password1.equals(password2)) {
            request.setAttribute("errorPassword", "Passwords don't match");
            request.setAttribute("errorPassword2", "Passwords don't match");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
            return;
        }
        if (!name.isEmpty()) {
            if (!name.matches(".[A-Za-zА-Яа-яёЁ]{3,}")) {
                request.setAttribute("errorName", "Name is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
                return;
            }
        }
        if (!surname.isEmpty()) {
            if (!surname.matches(".[A-Za-zА-Яа-яёЁ]{3,}")) {
                request.setAttribute("errorSurname", "Surname is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
                return;
            }
        }
        if (!telephone.isEmpty()) {
            if (!telephone.matches("[\\d]{10}")) {
                request.setAttribute("errorTelephone", "Telephone is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/CreateAccount.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}