package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTariffCommand implements Command {
    private final String URL = "/create_tariff";
    private TariffDAO tariffDAO;
    private final static Logger logger = Logger.getLogger(CreateTariffCommand.class);
    private final String NAME_REGEX = ".{4,45}";
    private final String PRICE_REGEX = "[0-9]+\\.[0-9]+|\\d+";
    private final String CREATE_TARIFF_JSP_PATH = "./WEB-INF/jsp/CreateTariff.jsp";


    public CreateTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        if ("GET".equals(request.getMethod())) {
            request.getSession().setAttribute("serviceId", request.getParameter("id"));
            request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
        }

        String name = request.getParameter("tariffName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        if (name.isEmpty()) {
            request.setAttribute("ErrorName", Localizer.getProper(request,"changeTariff.TariffNameEmpty"));
            request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
            return;
        }
        if (price.isEmpty()) {
            request.setAttribute("priceError", Localizer.getProper(request,"changeTariff.PriceEmpty"));
            request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
            return;
        }
        if (!price.matches(PRICE_REGEX)) {
            request.setAttribute("priceError", Localizer.getProper(request,"changeTariff.PriceIncorrect"));
            request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
            return;
        }
        if (!name.matches(NAME_REGEX)) {
            request.setAttribute("ErrorName", Localizer.getProper(request,"changeTariff.TariffNameIncorrect"));
            request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", Localizer.getProper(request,"changeTariff.DescriptionIncorrect"));
                request.getRequestDispatcher(CREATE_TARIFF_JSP_PATH).forward(request, response);
                return;
            }
        }

        Tariff tariff = Tariff.createTariff(name, description, Double.parseDouble(price), tariffDAO);
        logger.info("Create tariff: "+tariff.getId()+" "+request.getParameter("id"));
        tariffDAO.addTariffToService(tariff, (String) request.getSession().getAttribute("serviceId"));
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
