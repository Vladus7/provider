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
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
        }
        String amount = request.getParameter("amount");
        String card = request.getParameter("cardName");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String cvc = request.getParameter("cvc");
        if (amount.isEmpty()) {
            request.setAttribute("errorAmount", "Amount is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (card.isEmpty()) {
            request.setAttribute("errorCardName", "Card number is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (month.isEmpty() || year.isEmpty()) {
            request.setAttribute("errorDate", "Date is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (cvc.isEmpty()) {
            request.setAttribute("errorCVC", "CVC is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (!amount.matches("[0-9]+\\.[0-9]+|\\d+")) {
            request.setAttribute("errorAmount", "Amount is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (!card.matches("[\\d]{16}")) {
            request.setAttribute("errorCardName", "Card number is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (!month.matches("[\\d]{1,2}") || !year.matches("[\\d]{1,2}")) {
            request.setAttribute("errorDate", "Date is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (!cvc.matches("[\\d]{3}")) {
            request.setAttribute("errorCVC", "CVC is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }
        if (Double.parseDouble(amount) <= 5) {
            request.setAttribute("errorAmount", "Amount is small");
            request.getRequestDispatcher("./WEB-INF/jsp/Refill.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setBill(Double.parseDouble(request.getParameter("amount")) + user.getBill());
        userDAO.setUser(user);
        response.sendRedirect("/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
