package com.vlad.model.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.vlad.model.AppException;
import com.vlad.model.Sorter;
import com.vlad.model.dao.OrderDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.entity.Order;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import org.apache.log4j.Logger;

public class DownloadCommand implements Command {
    private final String URL = "/download";
    private UserDAO userDAO;
    private TariffDAO tariffDAO;
    private OrderDAO orderDAO;
    private final static Logger logger = Logger.getLogger(UserDAO.class);


    /**
     * @return Command object.
     */
    public DownloadCommand(UserDAO userDAO, TariffDAO tariffDAO, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.tariffDAO = tariffDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Process started");
        try {
            String elem = request.getParameter("elem");
            String id = request.getParameter("id");

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            List list;
            User user = (User) request.getSession().getAttribute("user");

            if ("user".equals(elem) && "admin".equals(user.getPermissions())) {
                list = getUserList(Sorter.sortUserBySpent(userDAO.getAllUser()));
            } else {
                if ("tariffs_user".equals(elem)) {
                    list = getOrderList(orderDAO.getAllUserOrders(
                            ((User) request.getSession().getAttribute("user")).getId() + ""), tariffDAO, userDAO);
                }else {
                list = getTariffList(tariffDAO.getTariffFromServiceId(id));}
            }



            document.add(list);
            document.add(Chunk.NEWLINE);

            document.close();

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException | AppException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }

    private static List getUserList(java.util.List<User> users) {
        List list = new List(true, 30);
        for (User user : users) {
            list.add(new ListItem(user.getLogin() + "  " + user.getName() + " " + user.getSurname() + " " + user.getSpent()));
        }
        return list;
    }

    private static List getTariffList(java.util.List<Tariff> tariffs) {
        List list = new List(true, 30);
        for (Tariff tariff : tariffs) {
            list.add(new ListItem(tariff.getName() + "  " + tariff.getPrice()));
        }
        return list;
    }

    private static List getOrderList(java.util.List<Order> orders, TariffDAO tariffDAO, UserDAO userDAO) throws AppException {
        List list = new List(true, 30);
        double cost = 0;
        for (Order order : orders) {
            Tariff tariff = tariffDAO.getTariff(order.getTariff_id() + "");
            long date = order.getEnd_support().getTime() - order.getStart_support().getTime();
            int month = (int)(date/2629746000L);
            cost = cost + tariff.getPrice()*month;
            list.add(new ListItem(tariff.getName() + "   " + tariff.getPrice() + "   " + order.getStart_support() + "   " + order.getEnd_support()+"   "+month));
        }
        list.add("You spent: "+ cost);
        return list;
    }

}
