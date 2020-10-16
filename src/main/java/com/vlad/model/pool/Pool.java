package com.vlad.model.pool;

import com.vlad.model.AppException;

import java.sql.Connection;
import java.sql.SQLException;

public interface Pool {
    Connection getConnection();

    void putConnection(Connection c) throws SQLException;
}
