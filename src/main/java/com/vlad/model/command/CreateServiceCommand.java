package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServiceCommand implements Command {
    private final String URL = "/create_service";
    private ServiceDAO serviceDAO;
    private final static Logger logger = Logger.getLogger(CreateServiceCommand.class);
    private final String IMAGE_REGEX = "^(http:\\/\\/\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private final String DESCRIPTION_REGEX = ".{4,45}";
    private final String CREATE_SERVICE_JSP_PATH = "./WEB-INF/jsp/CreateService.jsp";

    public CreateServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH).forward(request, response);
        }

        String image = request.getParameter("image");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");

        if (image.isEmpty()) {
            request.setAttribute("imageError", Localizer.getProper(request, "createService.ImageUREempty "));
            request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH).forward(request, response);
            return;
        }
        if (serviceName.isEmpty()) {
            request.setAttribute("serviceNameError", Localizer.getProper(request, "createService.ServiceNameEmpty"));
            request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH).forward(request, response);
            return;
        }
        if (!image.matches(IMAGE_REGEX)) {
            request.setAttribute("imageError", Localizer.getProper(request, "createService.ImageIncorrect"));
            request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH ).forward(request, response);
            return;
        }
        if (!serviceName.matches(DESCRIPTION_REGEX)) {
            request.setAttribute("serviceNameError", Localizer.getProper(request, "createService.ServiceNameIncorrec"));
            request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH ).forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", Localizer.getProper(request, "createService.DescriptionIncorrect"));
                request.getRequestDispatcher(CREATE_SERVICE_JSP_PATH ).forward(request, response);
                return;
            }
        }
        logger.info("Create service"+ serviceName);
        Service.createService(serviceName, image, description, serviceDAO);
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
