package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.Localizer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

public class ChangeTariffCommand implements Command {
    private final String URL = "/change_tariff";
    private TariffDAO tariffDAO;
    private final static Logger logger = Logger.getLogger(CreateTariffCommand.class);
    private final String CHANGE_TARIFF_PATH = "./WEB-INF/jsp/ChangeTariff.jsp";
    private final String NAME_REGEX =".{4,45}";
    private final String PRICE_REGEX = "[0-9]+\\.[0-9]+|\\d+";

    public ChangeTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        if ("GET".equals(request.getMethod())) {
            String id = request.getParameter("id");
            Tariff tariff = tariffDAO.getTariff(id);
            request.getSession().setAttribute("tariff", tariff);
            request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
            return;
        }

        String name = request.getParameter("tariffName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        System.out.println(name);

        HttpSession session = request.getSession();
        Tariff tariff = (Tariff) session.getAttribute("tariff");

        if (name.isEmpty()) {
            request.setAttribute("ErrorName", Localizer.getProper(request, "changeTariff.TariffNameEmpty"));
            request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
            return;
        }
        tariff.setName(name);
        if (price.isEmpty()) {
            request.setAttribute("priceError", Localizer.getProper(request, "changeTariff.PriceEmpty"));
            request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
            return;
        }
        tariff.setDescription(description);
        tariff.setPrice(Double.parseDouble(price));
        if (!price.matches(PRICE_REGEX)) {
            request.setAttribute("priceError", Localizer.getProper(request, "changeTariff.PriceIncorrect"));
            request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
            return;
        }
        if (!name.matches(NAME_REGEX)) {
            request.setAttribute("ErrorName", Localizer.getProper(request, "changeTariff.TariffNameIncorrect"));
            request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
            return;
        }
        if (!description.isEmpty()) {
            if (description.length() > 400) {
                request.setAttribute("descriptionError", Localizer.getProper(request, "changeTariff.DescriptionIncorrect"));
                request.getRequestDispatcher(CHANGE_TARIFF_PATH).forward(request, response);
                return;
            }
        }
        tariffDAO.setTariff(tariff);
        logger.info("Change Tariff");
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
