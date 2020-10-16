package com.vlad.model.command;

import com.vlad.model.Sorter;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TariffCommand implements Command {
    private final String url = "/tariff";
    private TariffDAO tariffDAO;

    public TariffCommand(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameterMap().containsKey("id")
                || !request.getParameterMap().containsKey("page")
                || !request.getParameterMap().containsKey("sorting")) {
            response.sendRedirect("http://localhost:8080/service");
            return;
        }
        String id = request.getParameter("id");
        String sorting = request.getParameter("sorting");
        int page = Integer.parseInt(request.getParameter("page"));
        List<Tariff> tariffList = tariffDAO.getTariffFromServiceId(id);
        switch (sorting) {
            case ("name"): {
                Sorter.sortTariffByName(tariffList);
                break;
            }
            case ("name_revers"): {
                Sorter.sortTariffByNameRevers(tariffList);
                break;
            }
            case ("cost"): {
                Sorter.sortTariffByCost(tariffList);
                break;
            }
        }
        List<Tariff> tariffs = new ArrayList<>();
        int endPage = page * 5;
        for (int i = endPage - 5; i < endPage; i++) {
            if (i == tariffList.size()) {
                break;
            }
            if (i > tariffList.size()) {
                request.getRequestDispatcher("/tariff?id=" + id + "&page=1&sorting=" + sorting).forward(request, response);
                return;
            }
            tariffs.add(tariffList.get(i));
        }
        int pages = tariffList.size() / 5;
        if (tariffList.size() % 5 != 0) pages++;
        if (page != 1) {
            request.setAttribute("previousPage", true);
        }
        if (page != pages) {
            request.setAttribute("nextPage", true);
        }
        request.setAttribute("previousPageLink", "http://localhost:8080/tariff?id=" + id + "&page=" + (page - 1) + "&sorting=" + sorting);
        request.setAttribute("currentPage", page);
        request.setAttribute("nextPageLink", "http://localhost:8080/tariff?id=" + id + "&page=" + (page + 1) + "&sorting=" + sorting);
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("id", id);
        request.setAttribute("tariffs", tariffs);
        request.getRequestDispatcher("./WEB-INF/jsp/tariffList.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
