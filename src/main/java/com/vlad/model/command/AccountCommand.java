package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountCommand implements Command {
    private TariffDAO tariffDAO;
    private UserDAO userDAO;
    private final String URL = "/account";
    private final String USER = "user";
    private final String TARIFFS = "tariffs";
    private final String GET = "GET";
    private final String ACCOUNT_JSP_PATH = "./WEB-INF/jsp/AccountPage.jsp";
    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final String TELEPHONE = "telephone";
    private final String REGEX_NAME = ".{3,45}";
    private final String REGEX_SURNAME = ".{3,45}";
    private final String REGEX_TELEPHONE = "[\\d]{10}";
    private final String ERROR_NAME = "errorName";
    private final String ERROR_SURNAME = "errorSurname";
    private final String ERROR_TELEPHONE = "errorTelephone";
    private final static Logger logger = Logger.getLogger(AccountCommand.class);


    /**
     * @return Command object.
     */
    public AccountCommand(TariffDAO tariffDAO, UserDAO userDAO) {
        this.tariffDAO = tariffDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        User user = (User) request.getSession().getAttribute(USER);
        List<Tariff> tariffs = tariffDAO.getTariffsByUser(user.getId() + "");
        request.setAttribute(TARIFFS, tariffs);
        if (GET.equals(request.getMethod())) {
            request.getRequestDispatcher(ACCOUNT_JSP_PATH).forward(request, response);
            return;
        }

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String telephone = request.getParameter(TELEPHONE);
        //valid input
        if (!name.isEmpty()) {
            if (!name.matches(REGEX_NAME)) {
                request.setAttribute(ERROR_NAME, Localizer.getProper(request, "accountPage.NameIncorrect"));
                request.getRequestDispatcher(ACCOUNT_JSP_PATH).forward(request, response);
                return;
            }
        }
        if (!surname.isEmpty()) {
            if (!surname.matches(REGEX_SURNAME)) {
                request.setAttribute(ERROR_SURNAME, Localizer.getProper(request, "accountPage.SurnameIncorrect"));
                request.getRequestDispatcher(ACCOUNT_JSP_PATH).forward(request, response);
                return;
            }
        }
        if (!telephone.isEmpty()) {
            if (!telephone.matches(REGEX_TELEPHONE)) {
                request.setAttribute(ERROR_TELEPHONE, Localizer.getProper(request, "accountPage.TelephoneIncorrect"));
                request.getRequestDispatcher(ACCOUNT_JSP_PATH).forward(request, response);
                return;
            }
        }

        logger.info("Account was change");
        HttpSession session = request.getSession();
        user.setName(name);
        user.setSurname(surname);
        user.setTelephone(telephone);
        userDAO.setUser(user);
        session.setAttribute(USER, user);
        response.sendRedirect(URL);
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
