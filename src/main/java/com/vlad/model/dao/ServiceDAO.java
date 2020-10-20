package com.vlad.model.dao;

import com.vlad.model.AppException;
import com.vlad.model.dao.entity.Service;
import com.vlad.model.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    private final int EXCEPTION_CODE = 500;
    private Pool dbPool;
    private final String ERROR = "ERROR: ";
    private final String INSERT_SERVLET = "INSERT INTO provider.service (service_name, image, description) VALUES (?,?,?)";
    private final String UPDATE_SERVLET = "UPDATE provider.service SET service_name = ?, image = ?, description = ?WHERE id = ?";
    private final String SELECT_SERVLET_BY_ID = "SELECT * FROM `provider`.service WHERE id = ?";
    private final String DELETE_SERVLET = "DELETE FROM provider.service WHERE id = ?";
    private final String SELECT_SERVLET = "SELECT * FROM `provider`.`service`";
    private final static Logger logger = Logger.getLogger(ServiceDAO.class);

    public ServiceDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    /**
     * @param service Create service
     */
    public void createService(Service service) throws AppException {
        try {
            logger.info("Create service " + service.getName());
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVLET, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getImage());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    service.setId((int) keys.getLong(1));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't create service", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * @param service update service.
     */
    public void setService(Service service) throws AppException {
        logger.info("Update service " + service.getId());
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVLET);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getImage());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.setInt(4, service.getId());
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException(EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * @param id
     * @return service.
     */
    public Service getService(String id) throws AppException {
        Service service = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVLET_BY_ID);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    service = new Service(Integer.parseInt(id),
                            resultSet.getString("service_name"),
                            resultSet.getString("image"),
                            resultSet.getString("description"));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get service", EXCEPTION_CODE, e.getMessage());
        }
        return service;
    }

    /**
     * @param id Delete service
     */
    public void deleteService(String id) throws AppException {
        logger.info("Delete service " + id);
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVLET);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't delete service", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * Returns all service.
     *
     * @return List of service.
     */
    public List<Service> getAllService() throws AppException {
        List<Service> services = new ArrayList();
        try {
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(SELECT_SERVLET)) {
                while (resultSet.next()) {
                    Service service = new Service(resultSet.getInt("id"),
                            resultSet.getString("service_name"),
                            resultSet.getString("image"),
                            resultSet.getString("description"));
                    services.add(service);
                }
            }
            dbPool.putConnection(connection);
            statement.close();
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get services", EXCEPTION_CODE, e.getMessage());
        }
        return services;
    }
}
