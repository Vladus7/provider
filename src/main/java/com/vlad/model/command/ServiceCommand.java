package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServiceCommand implements Command {
    private final String URL = "/service";
    private ServiceDAO serviceDAO;
    private final static Logger logger = Logger.getLogger(ServiceCommand.class);


    /**
     * @return Command object.
     */
    public ServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        logger.info("Process started");
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("services", serviceDAO.getAllService());
        request.getRequestDispatcher("./WEB-INF/jsp/Service.jsp").forward(request, response);
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
