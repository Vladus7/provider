package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.OrderDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Order;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class BuyCommand implements Command {
    private final String URL = "/buy";
    private TariffDAO tariffDAO;
    private OrderDAO orderDAO;
    private final static Logger logger = Logger.getLogger(BuyCommand.class);
    private final String PATH_BUY_JSP ="./WEB-INF/jsp/Buy.jsp";

    public BuyCommand(TariffDAO tariffDAO, OrderDAO orderDAO) {
        this.tariffDAO = tariffDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(PATH_BUY_JSP).forward(request, response);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Tariff tariff = tariffDAO.getTariff(request.getParameter("buy"));
        if (user.getBill() < tariff.getPrice()) {
            response.sendRedirect("/refill");
            return;
        }

        Order order = orderDAO.isTariffInOrder(tariff, user);
        String massage = "Thank for buying!";

        if (order != null) {
            orderDAO.prolongOrder(order);
            massage = "Thank for prolong tariff!";
        } else {
            order = orderDAO.buyTariff(tariff, user);
        }

        logger.info("User buy tariff"+tariff.getId()+" "+user.getId());
        session.setAttribute("user", user);
        session.setAttribute("massage", massage);
        session.setAttribute("date", order.getEnd_support());
        response.sendRedirect("/buy");
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
