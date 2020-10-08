package com.vlad.model.pool;

import java.sql.Connection;
import java.sql.SQLException;

public interface Pool {
   Connection getConnection() throws SQLException;

   void putConnection(Connection c) throws NullPointerException, SQLException;
}
