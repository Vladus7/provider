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

public class RegisterCommand implements Command {
    private final String URL = "/register";
    private UserDAO userDAO;
    private final static Logger logger = Logger.getLogger(RegisterCommand.class);
    private final String PATH_REGISTER_JSP = "./WEB-INF/jsp/Register.jsp";
    private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String PASSWORD_REGEX = ".{4,}";
    private final String NAME_REGEX = ".[A-Za-zА-Яа-яёЁ]{2,45}";
    private final String TELEPHONE_REGEX = "[\\d]{10}";

    /**
     * @return Command object.
     */
    public RegisterCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.info("Process started");
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
        }

        User user = null;
        String email = request.getParameter("email");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String telephone = request.getParameter("telephone");

        if (email.isEmpty()) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"register.emailIsEmpty"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (password1.isEmpty()) {
            request.setAttribute("errorPassword", Localizer.getProper(request,"register.PasswordIsEmpty"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (password2.isEmpty()) {
            request.setAttribute("errorPassword2", Localizer.getProper(request,"register.PasswordIsEmpty"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (!email.matches(EMAIL_REGEX)&&email.length()>255) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"register.EmailIncorrect"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (userDAO.checkEmail(email)) {
            request.setAttribute("errorLogin", Localizer.getProper(request,"register.EmailUsed"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (!password1.matches(PASSWORD_REGEX)) {
            request.setAttribute("errorPassword", Localizer.getProper(request,"register.PasswordIncorrect"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (!password2.matches(PASSWORD_REGEX)) {
            request.setAttribute("errorPassword2", Localizer.getProper(request,"register.PasswordIncorrect"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (!password1.equals(password2)) {
            request.setAttribute("errorPassword", Localizer.getProper(request,"register.PasswordsDon'tMatch"));
            request.setAttribute("errorPassword2", Localizer.getProper(request,"register.PasswordsDon'tMatch"));
            request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
            return;
        }
        if (!name.isEmpty()) {
            if (!name.matches(NAME_REGEX)) {
                request.setAttribute("errorName", Localizer.getProper(request,"register.NameIncorrect"));
                request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
                return;
            }
        }
        if (!surname.isEmpty()) {
            if (!surname.matches(NAME_REGEX)) {
                request.setAttribute("errorSurname", Localizer.getProper(request,"register.SurnameIncorrect"));
                request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
                return;
            }
        }
        if (!telephone.isEmpty()) {
            if (!telephone.matches(TELEPHONE_REGEX)) {
                request.setAttribute("errorTelephone", Localizer.getProper(request,"register.TelephoneIncorrect"));
                request.getRequestDispatcher(PATH_REGISTER_JSP).forward(request, response);
                return;
            }
        }

        user = User.createUser(email, Hasher.getHash(password1, "MD5"), 0.0, name, surname, telephone, 0.0, "user",false, userDAO);
        logger.info("User register");
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
