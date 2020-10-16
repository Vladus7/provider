package com.vlad.model.command;

import com.vlad.model.dao.ServiceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServiceCommand implements Command {
    private final String URL = "/service";
    private ServiceDAO serviceDAO;

    public ServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("services", serviceDAO.getAllService());
        request.getRequestDispatcher("./WEB-INF/jsp/Service.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
