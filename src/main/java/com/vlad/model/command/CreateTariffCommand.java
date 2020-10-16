package com.vlad.model.command;

import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTariffCommand implements Command {
    private final String URL = "/create_tariff";
    private TariffDAO tariffDAO;

    public CreateTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            request.getSession().setAttribute("serviceId", request.getParameter("id"));
            request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
        }
        String name = request.getParameter("tariffName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        if (name.isEmpty()) {
            request.setAttribute("ErrorName", "Tariff name is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
            return;
        }
        if (price.isEmpty()) {
            request.setAttribute("priceError", "Price is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
            return;
        }
        if (!price.matches("[0-9]+\\.[0-9]+|\\d+")) {
            request.setAttribute("priceError", "Price is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
            return;
        }
        if (!name.matches(".{4,45}")) {
            request.setAttribute("ErrorName", "Tariff name is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", "Description is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/CreateTariff.jsp").forward(request, response);
                return;
            }
        }
        Tariff tariff = Tariff.createTariff(name, description, Double.parseDouble(price), tariffDAO);
        System.out.println(request.getParameter("id"));
        tariffDAO.addTariffToService(tariff, (String) request.getSession().getAttribute("serviceId"));
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
