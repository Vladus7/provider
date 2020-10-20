package test;

import com.vlad.model.pool.DBPool;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBPoolTest {

    @Test
    public void getConnection() {
        DBPool dbPool = new DBPool("jdbc:mysql://localhost:3306/provider?verifyServerCertificate=false&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&user=vlad&password=vlad", 1);
        Assert.assertNotNull(dbPool.getConnection());
    }

    @Test
    public void putConnection() throws SQLException {
        DBPool dbPool = new DBPool("jdbc:mysql://localhost:3306/provider?verifyServerCertificate=false&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&user=vlad&password=vlad", 1);
        Connection connection = dbPool.getConnection();
        dbPool.putConnection(connection);
        Assert.assertEquals(connection, dbPool.getConnection());
    }
}