package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.Hasher;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    final static Logger logger = Logger.getLogger(LoginCommand.class);
    private final String URL = "/login";
    private UserDAO userDAO;
    private final String PATH_LOGIN_FORM_JSP = "./WEB-INF/jsp/LoginForm.jsp";
    private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String PASSWORD_REGEX = ".{4,}";

    /**
     * @return Command object.
     */
    public LoginCommand(UserDAO userDAO) {
        logger.debug("Login command is initialized");
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.debug("Login command process start");
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
        }

        User user = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user = userDAO.getUser(email);

        if (email.isEmpty()) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"login.emailIsEmpty"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }
        if (password.isEmpty()) {
            request.setAttribute("errorPassword", Localizer.getProper(request,"login.passwordIsEmpty"));
            request.getRequestDispatcher("./WEB-INF/jsp/LoginForm.jsp").forward(request, response);
            return;
        }
        if (!email.matches(EMAIL_REGEX)&&email.length()>255) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"login.emailIsIncorrect"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }
        if (!userDAO.checkEmail(email)) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"login.emailIsRegistered"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }
        if (!password.matches(PASSWORD_REGEX)) {
            request.setAttribute("errorPassword",  Localizer.getProper(request,"login.passwordIsIncorrect"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }
        if (!user.getPassword().equals(Hasher.getHash(password, "MD5")) && password.length() >= 4) {
            request.setAttribute("errorPassword", Localizer.getProper(request,"login.passwordIsIncorrect"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }
        if (user.isBlocking()) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"login.accountIsBanned"));
            request.getRequestDispatcher(PATH_LOGIN_FORM_JSP).forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2 * 60 * 60);
        session.setAttribute("user", user);
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
