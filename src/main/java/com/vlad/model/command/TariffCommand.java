package com.vlad.model.command;

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

    public TariffCommand(TariffDAO tariffDAO){
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void process( HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Tariff> tariffList = tariffDAO.getTariffFromServiceId(id);
            List<Tariff> tariffs = new ArrayList<>();
            int endPage = Integer.parseInt(request.getParameter("page"))*5;
        for (int i = endPage-5; i < endPage; i++) {
            if(i== tariffList.size()){break;}
            if(i>tariffList.size()){request.getRequestDispatcher("/tariff?id="+id+"&page=1").forward(request, response);
            return;}
            tariffs.add(tariffList.get(i));
        }
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("tariffs", tariffs);
            request.getRequestDispatcher("./WEB-INF/jsp/tariffList.jsp").forward(request, response);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
