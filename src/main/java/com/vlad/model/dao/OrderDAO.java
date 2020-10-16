package com.vlad.model.dao;

import com.vlad.model.dao.entity.Order;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.DBPool;
import com.vlad.model.pool.Pool;

import java.sql.*;
import java.util.Calendar;

public class OrderDAO {
    private Pool dbPool;

    public OrderDAO(Pool pool) {
        this.dbPool = pool;
    }

    public Order isTariffInOrder(Tariff tariff, User user) {
        Order order = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.order WHERE tariff_id = ? AND user_id = ? ");
            preparedStatement.setInt(1, tariff.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("tariff_id"),
                        resultSet.getDate("start_support"),
                        resultSet.getDate("end_support"));
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return order;
    }

    public void buyTariff(Tariff tariff, User user) {
        Connection connection = null;
        try {
            connection = dbPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.order (user_id, tariff_id, start_support , end_support ) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getId() + "");
            preparedStatement.setString(2, tariff.getId() + "");

            java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
            preparedStatement.setDate(3, todaysDate);
            Calendar cal = Calendar.getInstance();
            java.util.Date date = new java.util.Date();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            preparedStatement.setDate(4, new Date(cal.getTimeInMillis()));
            preparedStatement.execute();
            user.setBill(Math.floor((user.getBill() - tariff.getPrice()) * 100) / 100);
            user.setSpent(Math.floor((user.getSpent() + tariff.getPrice()) * 100) / 100);

            PreparedStatement preparedStatementUser = connection.prepareStatement("UPDATE provider.account SET bill = ?, spent = ?  WHERE id = ?");
            preparedStatementUser.setDouble(1, user.getBill());
            preparedStatementUser.setDouble(2, user.getSpent());
            preparedStatementUser.setDouble(3, user.getId());
            preparedStatementUser.execute();
            connection.commit();
            connection.setAutoCommit(true);
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public void prolongOrder(Order order) {
        try {
            Connection connection = dbPool.getConnection();

            java.sql.Date date = order.getEnd_support();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE provider.order SET end_support = ? WHERE id = ?");
            preparedStatement.setDate(1, new Date(cal.getTimeInMillis()));
            preparedStatement.setInt(2, order.getId());
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }
}
