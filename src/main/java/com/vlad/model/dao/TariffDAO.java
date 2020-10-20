package com.vlad.model.dao;

import com.vlad.model.AppException;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TariffDAO {
    private Pool dbPool;
    private final int EXCEPTION_CODE = 500;
    private final String ERROR = "ERROR: ";
    private final String CREATE_TARIFF = "INSERT INTO provider.tariff (tariff_name, description, price) VALUES (?,?,?)";
    private final String UPDATE_TARIFF = "UPDATE provider.tariff SET tariff_name = ?, description = ?, price=? WHERE id = ?";
    private final String SELECT_TARIFF_BY_ID = "SELECT * FROM `provider`.tariff WHERE id= ?";
    private final String DELETE_TARIFF = "DELETE FROM provider.tariff_service WHERE tariff_id = ?";
    private final String SELECT_TARIFF_BY_SERVICE_ID = "SELECT * FROM `provider`.tariff WHERE id IN (SELECT tariff_id FROM `provider`.tariff_service WHERE service_id = ?)";
    private final String CREATE_TARIFF_IN_SERVICE = "INSERT INTO provider.tariff_service (tariff_id, service_id) VALUES (?,?)";
    private final String SELECT_TARIFF_BY_USER_ID = "SELECT * FROM `provider`.tariff WHERE id IN (SELECT tariff_id FROM `provider`.`order` WHERE user_id = ?)";
    private final static Logger logger = Logger.getLogger(TariffDAO.class);

    public TariffDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    /**
     * Create tariff.
     *
     * @param tariff
     */
    public void createTariff(Tariff tariff) throws AppException {
        logger.info("Create tariff " + tariff.getId());
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TARIFF, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setString(2, tariff.getDescription());
            preparedStatement.setDouble(3, tariff.getPrice());
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    tariff.setId((int) keys.getLong(1));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't create tariff", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * Update tariff.
     *
     * @param tariff
     */
    public void setTariff(Tariff tariff) throws AppException {
        logger.info("Update tariff " + tariff.getId());
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TARIFF);
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setString(2, tariff.getDescription());
            preparedStatement.setDouble(3, tariff.getPrice());
            preparedStatement.setInt(4, tariff.getId());
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't save tariff", EXCEPTION_CODE, e.getMessage());
        }

    }

    /**
     * Returns tariff.
     *
     * @param id
     * @return tariff.
     */
    public Tariff getTariff(String id) throws AppException {
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TARIFF_BY_ID);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                }
            }
            dbPool.putConnection(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get tariff", EXCEPTION_CODE, e.getMessage());
        }
        return tariff;
    }

    /**
     * Delete tariff.
     *
     * @param id
     */
    public void deleteTariff(String id) throws AppException {
        logger.info("Delete tariff " + id);
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TARIFF);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't delete tariff", EXCEPTION_CODE, e.getMessage());
        }

    }

    /**
     * Returns tariffs by service
     *
     * @return List of tariff.
     */
    public List<Tariff> getTariffFromServiceId(String id) throws AppException {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TARIFF_BY_SERVICE_ID);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    tariffs.add(tariff);
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get tariff", EXCEPTION_CODE, e.getMessage());
        }
        return tariffs;
    }

    /**
     * Add tariff to service
     *
     * @param tariff, serviceId
     */
    public void addTariffToService(Tariff tariff, String serviceId) throws AppException {
        try {
            logger.info("Add tariff to service " + tariff.getId() + " " + serviceId);
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TARIFF_IN_SERVICE);
            preparedStatement.setInt(1, tariff.getId());
            preparedStatement.setString(2, serviceId);
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Add service", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * Returns all tariff.
     *
     * @return List of tariff by user.
     */
    public List<Tariff> getTariffsByUser(String id) throws AppException {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TARIFF_BY_USER_ID);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    tariffs.add(tariff);
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get user's tariff", EXCEPTION_CODE, e.getMessage());
        }
        return tariffs;
    }
}
