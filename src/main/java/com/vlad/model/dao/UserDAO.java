package com.vlad.model.dao;

import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Pool dbPool;

    public UserDAO(Pool dbPool){
        this.dbPool = dbPool;
    }

    public void createUser(User user) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.account (login, password, permissions,language, bill, name, surname, telephone, spent) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setString(4, "EN");
            preparedStatement.setDouble(5, 0);
            preparedStatement.setString(6, "");
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, "");
            preparedStatement.setDouble(9, 0);
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId((int) keys.getLong(1));
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }
//update
    public void setUser(User user) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE provider.account SET login = ?, password = ?, permissions = ?, language=?, bill = ?, name = ?, surname = ?, telephone = ?, spent = ?  WHERE id = ?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setString(4, user.getLanguage());
            preparedStatement.setDouble(5, user.getBill());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.setString(8, user.getTelephone());
            preparedStatement.setDouble(9, user.getSpent());
            preparedStatement.setInt(10, user.getId());
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public User getUser(String login) {
        User user = null;
        try {Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.account WHERE login = ?");
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("permissions"),
                        resultSet.getString("language"),
                        resultSet.getDouble("bill"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("telephone"),
                        resultSet.getDouble("spent"));
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return user;
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        User user = null;
        try {Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.account");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("permissions"),
                        resultSet.getString("language"),
                        resultSet.getDouble("bill"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("telephone"),
                        resultSet.getDouble("spent"));
            }
            users.add(user);
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return users;
    }
}
