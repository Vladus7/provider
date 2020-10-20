package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command{
    private final String URL = "/logout";
    private final static Logger logger = Logger.getLogger(LogoutCommand.class);


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.info("Process started");
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
