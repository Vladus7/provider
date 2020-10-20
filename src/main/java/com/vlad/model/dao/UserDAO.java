package com.vlad.model.dao;

import com.vlad.model.AppException;
import com.vlad.model.dao.entity.User;
import com.vlad.model.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for User entity.
 */
public class UserDAO {
    private Pool dbPool;
    private final int EXCEPTION_CODE = 500;
    private final String ERROR = "ERROR: ";
    private final String CREATE_USER = "INSERT INTO provider.account (login, password, permissions, bill, name, surname, telephone, spent, blocking) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_USER = "UPDATE provider.account SET login = ?, password = ?, permissions = ?,  bill = ?, name = ?, surname = ?, telephone = ?, spent = ?, blocking = ?  WHERE id = ?";
    private final String SELECT_USER_BY_ID = "SELECT * FROM `provider`.account WHERE id = ?";
    private final String SELECT_ALL_USERS = "SELECT * FROM `provider`.account";
    private final String SELECT_USER_BY_LOGIN = "SELECT * FROM `provider`.account WHERE login = ?";
    private final static Logger logger = Logger.getLogger(UserDAO.class);

    public UserDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param user User identifier.
     * @return User entity.
     */
    public void createUser(User user) throws AppException {
        try {
            logger.info("Create user" + user.getLogin());
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setDouble(4, 0);
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getTelephone());
            preparedStatement.setDouble(8, 0);
            preparedStatement.setBoolean(9, user.isBlocking());
            preparedStatement.execute();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId((int) keys.getLong(1));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't register user", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * Update user.
     *
     * @param user user to update.
     */
    public void setUser(User user) throws AppException {
        try {
            logger.info("Save user" + user.getLogin());
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPermissions());
            preparedStatement.setDouble(4, user.getBill());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getTelephone());
            preparedStatement.setDouble(8, user.getSpent());
            preparedStatement.setBoolean(9, user.isBlocking());
            preparedStatement.setInt(10, user.getId());
            preparedStatement.execute();
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't update user", EXCEPTION_CODE, e.getMessage());
        }
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param login User identifier.
     * @return User entity.
     */
    public User getUser(String login) throws AppException {
        User user = null;
        try {
            logger.info(login);
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("permissions"),
                            resultSet.getDouble("bill"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("telephone"),
                            resultSet.getDouble("spent"),
                            resultSet.getBoolean("blocking"));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't find user", EXCEPTION_CODE, e.getMessage());
        }
        return user;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    public User getUserById(String id) throws AppException {
        User user = null;
        try {
            logger.info(id);
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("permissions"),
                            resultSet.getDouble("bill"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("telephone"),
                            resultSet.getDouble("spent"),
                            resultSet.getBoolean("blocking"));
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get user", EXCEPTION_CODE, e.getMessage());
        }
        return user;
    }

    /**
     * Returns all users.
     *
     * @return User list.
     */
    public List<User> getAllUser() throws AppException {
        List<User> users = new ArrayList<>();
        User user = null;
        try {
            logger.info("Get all user start");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("permissions"),
                            resultSet.getDouble("bill"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("telephone"),
                            resultSet.getDouble("spent"),
                            resultSet.getBoolean("blocking"));
                    users.add(user);
                }
            }
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't get users", EXCEPTION_CODE, e.getMessage());
        }
        return users;
    }

    /**
     * Returns a boolean.
     *
     * @param email User check email on login
     * @return false or true.
     */
    public boolean checkEmail(String email) throws AppException {
        boolean answer = false;
        try {
            logger.info(email);
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    answer = true;
                }
            }
            preparedStatement.close();
            dbPool.putConnection(connection);
        } catch (SQLException e) {
            logger.error(ERROR + e);
            throw new AppException("Can't check email", EXCEPTION_CODE, e.getMessage());
        }
        return answer;
    }
}
