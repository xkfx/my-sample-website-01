package org.sample.manager;

import org.sample.db.LocalConnectionFactory;
import org.sample.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class LocalConnectionProxy {

    public static void setAutoCommit(boolean autoCommit) {
        try {
            Connection conn = LocalConnectionFactory.getConnection();
            conn.setAutoCommit(autoCommit);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void commit() {
        try {
            Connection conn = LocalConnectionFactory.getConnection();
            conn.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void rollback() {
        try {
            Connection conn = LocalConnectionFactory.getConnection();
            conn.rollback();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void close() {
        try {
            Connection conn = LocalConnectionFactory.getConnection();
            conn.close();
            LocalConnectionFactory.removeLocalConnection();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
