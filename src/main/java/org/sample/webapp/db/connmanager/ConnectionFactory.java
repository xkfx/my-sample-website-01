package org.sample.webapp.db.connmanager;

import org.sample.webapp.db.datasource.HikariCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 线程池版
 */
public class ConnectionFactory {

    private ConnectionFactory() {
        // Exists to defeat instantiation
    }

    static {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // catalina.out
        }
    }

    private static final ThreadLocal<Connection> LocalConnectionHolder = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection conn = LocalConnectionHolder.get();
        if (conn == null || conn.isClosed()) {
            conn = HikariCPDataSource.getConnection();
            LocalConnectionHolder.set(conn);
        }
        return conn;
    }

    public static void removeLocalConnection() {
        LocalConnectionHolder.remove();
    }
}
