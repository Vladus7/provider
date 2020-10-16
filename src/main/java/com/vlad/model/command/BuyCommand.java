package com.vlad.model.command;

import com.vlad.model.dao.OrderDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.entity.Order;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyCommand implements Command {
    private final String URL = "/buy";
    private TariffDAO tariffDAO;
    private OrderDAO orderDAO;

    public BuyCommand(TariffDAO tariffDAO, OrderDAO orderDAO) {
        this.tariffDAO = tariffDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("./WEB-INF/jsp/Buy.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Tariff tariff = tariffDAO.getTariff(request.getParameter("buy"));
        if (user.getBill() < tariff.getPrice()) {
            response.sendRedirect("http://localhost:8080/refill");
            return;
        }
        Order order = orderDAO.isTariffInOrder(tariff, user);
        if (order != null) {
            orderDAO.prolongOrder(order);
            session.setAttribute("user", user);
            session.setAttribute("massage", "Thank for prolong tariff!");
            response.sendRedirect("/buy");
            return;
        }
        orderDAO.buyTariff(tariff, user);
        session.setAttribute("user", user);
        session.setAttribute("massage", "Thank for buying!");
        response.sendRedirect("/buy");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
