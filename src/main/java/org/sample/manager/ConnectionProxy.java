package org.sample.manager;

import org.sample.db.ConnectionFactory;
import org.sample.exception.DaoException;

import java.sql.Connection;

/**
 * 对应线程池版本ConnectionFactory
 */
public class ConnectionProxy {
    public static void setAutoCommit(boolean autoCommit) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(autoCommit);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void commit() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void rollback() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.rollback();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void close() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.close();
            ConnectionFactory.removeLocalConnection();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    // TODO 设置隔离级别
}
