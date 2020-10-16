package com.vlad.model.command;

import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountCommand implements Command {
    private final String URL = "/account";
    private TariffDAO tariffDAO;
    private UserDAO userDAO;

    public AccountCommand(TariffDAO tariffDAO, UserDAO userDAO) {
        this.tariffDAO = tariffDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Tariff> tariffs = tariffDAO.getTariffsByUser(user.getId() + "");
        request.setAttribute("tariffs", tariffs);
        if ("GET".equals(request.getMethod())) {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("./WEB-INF/jsp/AccountPage.jsp").forward(request, response);
            return;
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String telephone = request.getParameter("telephone");
        System.out.println(name + " " + surname + " " + telephone);
        if (!name.isEmpty()) {
            if (!name.matches(".{3,}")) {
                request.setAttribute("errorName", "Name is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/AccountPage.jsp").forward(request, response);
                return;
            }
        }
        if (!surname.isEmpty()) {
            if (!surname.matches(".{3,}")) {
                request.setAttribute("errorSurname", "Surname is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/AccountPage.jsp").forward(request, response);
                return;
            }
        }
        if (!telephone.isEmpty()) {
            if (!telephone.matches("[\\d]{10}")) {
                request.setAttribute("errorTelephone", "Telephone is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/AccountPage.jsp").forward(request, response);
                return;
            }
        }
        HttpSession session = request.getSession();
        user.setName(name);
        user.setSurname(surname);
        user.setTelephone(telephone);
        userDAO.setUser(user);
        session.setAttribute("user", user);
        response.sendRedirect("http://localhost:8080/account");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
