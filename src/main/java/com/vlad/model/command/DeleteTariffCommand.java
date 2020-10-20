package com.vlad.model.command;

import com.vlad.model.AppException;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTariffCommand implements Command {
    private final String URL = "/delete_tariff";
    private TariffDAO tariffDAO;
    private final static Logger logger = Logger.getLogger(DeleteTariffCommand.class);


    /**
     * @return Command object.
     */
    public DeleteTariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        logger.info("Delete tariff " + request.getParameter("id"));
        tariffDAO.deleteTariff(request.getParameter("id"));
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
