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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeServiceCommand implements Command {
    private final String URL = "/change_service";
    private ServiceDAO serviceDAO;
    private final static Logger logger = Logger.getLogger(ChangeServiceCommand.class);
    private final String PATH_CHANGE_SERVICE_JSP = "./WEB-INF/jsp/ChangeService.jsp";
    private final String SERVICE_IMAGE_REGEX = "^(http:\\/\\/\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private final String SERVICE_NAME_REGEX = ".{4,45}";

    public ChangeServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        if ("GET".equals(request.getMethod())) {
            String id = request.getParameter("id");
            Service service = serviceDAO.getService(id);
            request.getSession().setAttribute("service", service);
            request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
            return;
        }

        String image = request.getParameter("image");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();
        Service service = (Service) session.getAttribute("service");
        service.setDescription(description);
        service.setImage(image);
        service.setName(serviceName);

        if (image.isEmpty()) {
            request.setAttribute("imageError", Localizer.getProper(request, "createService.ImageUREempty "));
            request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
            return;
        }
        if (serviceName.isEmpty()) {
            request.setAttribute("serviceNameError", Localizer.getProper(request, "createService.ServiceNameEmpty"));
            request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
            return;
        }
        if (!image.matches(SERVICE_IMAGE_REGEX)) {
            request.setAttribute("imageError", Localizer.getProper(request, "createService.ImageIncorrect"));
            request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
            return;
        }
        if (!serviceName.matches(SERVICE_NAME_REGEX)) {
            request.setAttribute("serviceNameError", Localizer.getProper(request, "createService.ServiceNameIncorrec"));
            request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", Localizer.getProper(request, "createService.DescriptionIncorrect"));
                request.getRequestDispatcher(PATH_CHANGE_SERVICE_JSP).forward(request, response);
                return;
            }
        }
        logger.info("Service was change"+service.getId());
        serviceDAO.setService(service);
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
