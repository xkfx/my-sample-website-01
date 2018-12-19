package org.sample.webapp.db.connmanager;

import org.sample.webapp.exception.DaoException;
import org.sample.webapp.db.connmanager.ConnectionFactory;

import java.sql.Connection;

/**
 * TODO 设计不合理，待修改
 * 对应线程池版本ConnectionFactory，方便在Service层进行事务控制
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
