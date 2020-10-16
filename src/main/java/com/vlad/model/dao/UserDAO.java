package com.vlad.model.dao;

import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Pool dbPool;

    public UserDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    public void createUser(User user) {
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO provider.account (login, password, permissions, bill, name, surname, telephone, spent) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setDouble(4, 0);
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getTelephone());
            preparedStatement.setDouble(8, 0);
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE provider.account SET login = ?, password = ?, permissions = ?,  bill = ?, name = ?, surname = ?, telephone = ?, spent = ?  WHERE id = ?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setDouble(4, user.getBill());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getTelephone());
            preparedStatement.setDouble(8, user.getSpent());
            preparedStatement.setInt(9, user.getId());
            preparedStatement.execute();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            //logger.log(Level.SEVERE, ERROR, e);
        }
    }

    public User getUser(String login) {
        User user = null;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.account WHERE login = ?");
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("permissions"),
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
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.account");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("permissions"),
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

    public boolean checkEmail(String email) {
        boolean answer = false;
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `provider`.account WHERE login = ?");
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                answer = true;
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            System.out.println(e);
            // logger.log(Level.SEVERE, ERROR, e);
        }
        return answer;
    }
}
