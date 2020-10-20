package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.Sorter;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountListCommand implements Command {
    private final static Logger logger = Logger.getLogger(AccountListCommand.class);
    private final String URL = "/accountList";
    private UserDAO userDAO;
    private final String PATH_USER_LIST_JSP = "./WEB-INF/jsp/userList.jsp";

    public AccountListCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.info("Process started");
        List<User> users = userDAO.getAllUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher(PATH_USER_LIST_JSP).forward(request, response);
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
