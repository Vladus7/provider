package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BannedCommand implements Command {
    private final static Logger logger = Logger.getLogger(BannedCommand.class);
    private final String URL = "/banned";
    private UserDAO userDAO;

    public BannedCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        User user = userDAO.getUserById(request.getParameter("id"));
        user.setBlocking("banned".equals(request.getParameter("command")) && !"admin".equals(user.getPermissions()));
        userDAO.setUser(user);
        logger.info("User was banned "+ user.getLogin());
        response.sendRedirect("/accountList");
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
