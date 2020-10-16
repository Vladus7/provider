package com.vlad.model.command;

import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeTariffCommand implements Command {
    private final String URL = "/change_tariff";
    private TariffDAO tariffDAO;

    public ChangeTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            String id = request.getParameter("id");
            Tariff tariff = tariffDAO.getTariff(id);
            request.getSession().setAttribute("tariff", tariff);
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
            return;
        }
        String name = request.getParameter("tariffName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();
        Tariff tariff = (Tariff) session.getAttribute("tariff");
        if (name.isEmpty()) {
            request.setAttribute("ErrorName", "Tariff name is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
            return;
        }
        tariff.setName(name);
        if (price.isEmpty()) {
            request.setAttribute("priceError", "Price is empty");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
            return;
        }
        tariff.setDescription(description);
        tariff.setPrice(Double.parseDouble(price));
        if (!price.matches("[0-9]+\\.[0-9]+|\\d+")) {
            request.setAttribute("priceError", "Price is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
            return;
        }
        if (!name.matches(".{4,45}")) {
            request.setAttribute("ErrorName", "Tariff name is incorrect");
            request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", "Description is incorrect");
                request.getRequestDispatcher("./WEB-INF/jsp/ChangeTariff.jsp").forward(request, response);
                return;
            }
        }
        tariffDAO.setTariff(tariff);
        response.sendRedirect("http://localhost:8080/service");
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
