package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RefillCommand implements Command {
    private final String URL = "/refill";
    private UserDAO userDAO;
    private final static Logger logger = Logger.getLogger(RefillCommand.class);
    private final String PATH_REFILL_JSP = "./WEB-INF/jsp/Refill.jsp";

    /**
     * @return Command object.
     */
    public RefillCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.info("Process started");
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
        }

        String amount = request.getParameter("amount");
        String card = request.getParameter("cardName");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String cvc = request.getParameter("cvc");

        if (amount.isEmpty()) {
            request.setAttribute("errorAmount", Localizer.getProper(request,"refill.AmountEmpty"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (card.isEmpty()) {
            request.setAttribute("errorCardName", Localizer.getProper(request,"refill.DateEmpty"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (month.isEmpty() || year.isEmpty()) {
            request.setAttribute("errorDate", Localizer.getProper(request,"refill.DateEmpty"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (cvc.isEmpty()) {
            request.setAttribute("errorCVC", Localizer.getProper(request,"refill.CVCEmpty"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (!amount.matches("[0-9]+\\.[0-9]+|\\d+")) {
            request.setAttribute("errorAmount", Localizer.getProper(request,"refill.AmountIncorrect"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (!card.matches("[\\d]{16}")) {
            request.setAttribute("errorCardName", Localizer.getProper(request,"refill.CardNumberIncorrect"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (!month.matches("[\\d]{1,2}") || !year.matches("[\\d]{1,2}")) {
            request.setAttribute("errorDate", Localizer.getProper(request,"refill.DateIncorrect"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (!cvc.matches("[\\d]{3}")) {
            request.setAttribute("errorCVC", Localizer.getProper(request,"refill.CVCIncorrect"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }
        if (Double.parseDouble(amount) <= 5) {
            request.setAttribute("errorAmount", Localizer.getProper(request,"refill.AmountSmall"));
            request.getRequestDispatcher(PATH_REFILL_JSP).forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setBill(Double.parseDouble(request.getParameter("amount")) + user.getBill());
        userDAO.setUser(user);

        logger.info("User refill account");

        response.sendRedirect("/service");
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
