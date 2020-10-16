package com.vlad.model.command;

import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.entity.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeServiceCommand implements Command{
    private final String URL = "/change_service";
    private ServiceDAO serviceDAO;

    public ChangeServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("GET".equals(request.getMethod())){
            String id =  request.getParameter("id");
            Service service = serviceDAO.getService(id);
            request.getSession().setAttribute("service",service);
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
        return;}
        String image = request.getParameter("image");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();
        Service service = (Service) session.getAttribute("service");
        service.setDescription(description);
        service.setImage(image);
        service.setName(serviceName);
        if (image.isEmpty()){
            request.setAttribute("imageError","Image URL is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
            return;
        }
        if (serviceName.isEmpty()){request.setAttribute("serviceNameError","Service name is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
            return;
        }
        if (!image.matches("^(http:\\/\\/\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")){
            request.setAttribute("imageError","Image is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
            return;
        }
        if (!serviceName.matches(".{4,45}")){request.setAttribute("serviceNameError","Service name is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
            return;
        }
        if(!description.isEmpty()){
            if(description.length()>400){
                request.setAttribute("descriptionError","Description is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/ChangeService.jsp").forward(request, response);
                return;
            }}
        serviceDAO.setService(service);
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
