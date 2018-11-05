package org.sample.db;

import org.sample.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LocalConnectionFactory {

    private LocalConnectionFactory() {
        // Exists to defeat instantiation
    }

    private static ResourceBundle rb = ResourceBundle.getBundle("org.sample.db.db-config");

    private static final String JDBC_URL = rb.getString("jdbc.url");

    private static final String JDBC_USER = rb.getString("jdbc.username");

    private static final String JDBC_PASSWORD = rb.getString("jdbc.password");

    private static final ThreadLocal<Connection> LocalConnectionHolder = new ThreadLocal<>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 虽然说JDBC4之后已经不再需要Class.forName，但是能否
            // 自动注册和环境、版本的相关性很大，所以安全起见还是加上这句比较好。
        } catch (ClassNotFoundException e) {
            // TODO 日志
            throw new DaoException("could not register JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = LocalConnectionHolder.get();
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            LocalConnectionHolder.set(conn);
        }
        return conn;
    }

    public static void removeLocalConnection() {
        // TODO 应该先关掉再remove?
        LocalConnectionHolder.remove();
    }
}
