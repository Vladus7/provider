package com.vlad.model.pool;

import com.vlad.model.AppException;
import com.vlad.model.pool.Pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * DBPool.
 */
public class DBPool implements Pool {
    private String url;
    private List<Connection> connections = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    public DBPool(String url, int maxConnections) {
        this.url = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < maxConnections; i++) {
                connections.add(createConnection());
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * @return connection.
     */
    public synchronized Connection getConnection() {
        Connection newConn;
        if (connections.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = connections.get(connections.size() - 1);
            connections.remove(newConn);
        }
        usedConnections.add(newConn);
        return newConn;
    }

    /**
     * Returns connection into pool
     *
     * @param c
     */
    public synchronized void putConnection(Connection c) throws SQLException {
        if (c != null) {
            if (usedConnections.remove(c)) {
                connections.add(c);
            } else {
                throw new SQLException("Connection not in the used Connection array");
            }
        }
    }

}
