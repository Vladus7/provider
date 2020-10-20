package com.vlad.model.dao;

import com.vlad.model.AppException;
import com.vlad.model.dao.entity.Order;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Calendar;

public class OrderDAO {
    private Pool dbPool;
    private final int EXCEPTION_CODE = 500;
    private final String ERROR = "ERROR: ";
    private final String SELECT_TARIFF_IN_ORDER = "SELECT * FROM `provider`.order WHERE tariff_id = ? AND user_id = ? ";
    private final String INSERT_INTO_ORDER = "INSERT INTO provider.order (user_id, tariff_id, start_support , end_support ) VALUES (?,?,?,?)";
    private final String UPDATE_ACCOUNT = "UPDATE provider.account SET bill = ?, spent = ?  WHERE id = ?";
    private final String UPDATE_ORDER = "UPDATE provider.order SET end_support = ? WHERE id = ?";
    private final static Logger logger = Logger.getLogger(OrderDAO.class);


    public OrderDAO(Pool pool) {
        this.dbPool = pool;
    }

    /**
     * @param tariff, user
     *                <p>
     *                check is tariff in order
     * @return Order.
     */
    public Order isTariffInOrder(Tariff tariff, User user) throws AppException {
        Order order = null;
        logger.info("Check order " + tariff.getId() +" "+ user.getId());
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TARIFF_IN_ORDER);
            preparedStatement.setInt(1, tariff.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order = new Order(resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("tariff_id"),
                            resultSet.getDate("start_support"),
                            resultSet.getDate("end_support"));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't found tariff", EXCEPTION_CODE, e.getMessage());
        }
        return order;
    }

    /**
     * @param tariff, user
     *                <p>
     *                create tariff for entity
     * @return Order.
     */
    public Order buyTariff(Tariff tariff, User user) throws AppException {
        logger.info("Create order " + tariff.getId() +" "+ user.getId());
        Connection connection = null;
        Order order = null;
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        Date dateWithMonth = addMonth(date);

        try {
            connection = dbPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getId() + "");
            preparedStatement.setString(2, tariff.getId() + "");
            preparedStatement.setDate(3, date);
            preparedStatement.setDate(4, dateWithMonth);
            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    order = new Order((int) keys.getLong(1), user.getId(),
                            tariff.getId(), date, dateWithMonth);
                }
            }

            user.setBill(Math.floor((user.getBill() - tariff.getPrice()) * 100) / 100);
            user.setSpent(Math.floor((user.getSpent() + tariff.getPrice()) * 100) / 100);

            PreparedStatement preparedStatementUser = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatementUser.setDouble(1, user.getBill());
            preparedStatementUser.setDouble(2, user.getSpent());
            preparedStatementUser.setDouble(3, user.getId());
            preparedStatementUser.execute();
            connection.commit();
            connection.setAutoCommit(true);
            preparedStatement.close();
            preparedStatementUser.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new AppException(EXCEPTION_CODE, ex.getMessage());
            }
            logger.error(ERROR + e);
            throw new AppException("Can't buy tariff", EXCEPTION_CODE, e.getMessage());
        }
        return order;
    }

    /**
     * prolong order
     */
    public void prolongOrder(Order order) throws AppException {
        logger.info("Prolong order " + order.getId());
        try {
            Connection connection = dbPool.getConnection();

            java.sql.Date date = order.getEnd_support();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setDate(1, addMonth(date));
            preparedStatement.setInt(2, order.getId());
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't prolong tariff", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * @param date add month to date
     * @return Date.
     */
    private static Date addMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return new Date(cal.getTimeInMillis());
    }
}
