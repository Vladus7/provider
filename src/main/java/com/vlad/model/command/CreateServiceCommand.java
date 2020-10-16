package com.vlad.model.command;

import com.vlad.model.Hasher;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServiceCommand implements Command {
    private final String URL = "/create_service";
    private ServiceDAO serviceDAO;

    public CreateServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
        }
        String image = request.getParameter("image");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        if (image.isEmpty()) {
            request.setAttribute("imageError", "Image URL is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
            return;
        }
        if (serviceName.isEmpty()) {
            request.setAttribute("serviceNameError", "Service name is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
            return;
        }
        if (!image.matches("^(http:\\/\\/\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")) {
            request.setAttribute("imageError", "Image is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
            return;
        }
        if (!serviceName.matches(".{4,45}")) {
            request.setAttribute("serviceNameError", "Service name is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", "Description is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/CreateService.jsp").forward(request, response);
                return;
            }
        }
        Service.createService(serviceName, image, description, serviceDAO);
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
