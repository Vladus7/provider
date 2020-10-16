package com.vlad.model.command;

import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTariffCommand implements Command {
    private final String URL = "/delete_tariff";
    private TariffDAO tariffDAO;

    public DeleteTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tariffDAO.deleteTariff(request.getParameter("id"));
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
