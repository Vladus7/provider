package com.vlad.model.command;

import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.entity.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServiceCommand implements Command {
    private final String URL = "/delete_service";
    private ServiceDAO serviceDAO;

    public DeleteServiceCommand(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceDAO.deleteService(request.getParameter("id"));
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
