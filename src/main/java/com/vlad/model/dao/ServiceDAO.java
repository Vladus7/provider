package com.vlad.model.dao;

import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    private Pool dbPool;

    public ServiceDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    public void createService(Service service) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.service (service_name, image, description) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getImage());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    service.setId((int) keys.getLong(1));
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public void setService(Service service) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE provider.service SET service_name = ?, image = ?, description = ?WHERE id = ?");
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getImage());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.setInt(4, service.getId());
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public Service getService(String id) {
        Service service = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.service WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                service = new Service(Integer.parseInt(id),
                        resultSet.getString("service_name"),
                        resultSet.getString("image"),
                        resultSet.getString("description"));
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return service;
    }

    public void deleteService(String id) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM provider.service WHERE id = ?");
            preparedStatement.setString(1,
                    id);
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public List<Service> getAllService() {
        List<Service> services = new ArrayList();
        try {
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `provider`.`service`");
            while (resultSet.next()) {
                Service service = new Service(resultSet.getInt("id"),
                        resultSet.getString("service_name"),
                        resultSet.getString("image"),
                        resultSet.getString("description"));
                services.add(service);
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return services;
    }
}
