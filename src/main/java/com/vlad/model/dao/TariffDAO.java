package com.vlad.model.dao;

import com.vlad.model.dao.entity.Service;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TariffDAO {
    private Pool dbPool;

    public TariffDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    public void createTariff(Tariff tariff) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.tariff (tariff_name, description, price) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setString(2, tariff.getDescription());
            preparedStatement.setDouble(3, tariff.getPrice());
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    tariff.setId((int) keys.getLong(1));
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public void setTariff(Tariff tariff) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE provider.tariff SET tariff_name = ?, description = ?, price=? WHERE id = ?");
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setString(2, tariff.getDescription());
            preparedStatement.setDouble(3, tariff.getPrice());
            preparedStatement.setInt(4, tariff.getId());
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }

    }

    public Tariff getTariff(String id) {
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.tariff WHERE id= ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    //user.setId(Integer.parseInt(resultSet.getString("id")));
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return tariff;
    }

    public void deleteTariff(String id) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM provider.tariff_service WHERE tariff_id = ?");
            preparedStatement.setString(1,
                    id);
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            //logger.log(Level.SEVERE, ERROR, e);
        }

    }

    public List<Tariff> getAllTariff() {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.tariff");
            preparedStatement.execute();
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    tariffs.add(tariff);
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return tariffs;
    }

    public List<Tariff> getTariffFromServiceId(String id) {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.tariff WHERE id IN (SELECT tariff_id FROM `provider`.tariff_service WHERE service_id = ?)");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    tariffs.add(tariff);
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return tariffs;
    }

    public void addTariffToService(Tariff tariff, String serviceId) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.tariff_service (tariff_id, service_id) VALUES (?,?)");
            preparedStatement.setInt(1, tariff.getId());
            preparedStatement.setString(2, serviceId);
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public List<Tariff> getTariffsByUser(String id) {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        Tariff tariff = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.tariff WHERE id IN (SELECT tariff_id FROM `provider`.`order` WHERE user_id = ?)");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tariff = new Tariff(Integer.parseInt(resultSet.getString("id")), resultSet.getString("tariff_name"),
                            resultSet.getString("description"), resultSet.getDouble("price"));
                    tariffs.add(tariff);
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return tariffs;
    }
}
