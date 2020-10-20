package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServiceCommand implements Command {
    private final String URL = "/delete_service";
    private ServiceDAO serviceDAO;
    private final static Logger logger = Logger.getLogger(DeleteServiceCommand.class);


    /**
     * @return Command object.
     */
    public DeleteServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        logger.info("Process started");
        serviceDAO.deleteService(request.getParameter("id"));
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
