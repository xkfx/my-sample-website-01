package org.sample.webapp.db.queryrunner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.sample.webapp.db.connmanager.ConnectionFactory;
import org.sample.webapp.exception.DaoException;

import java.sql.SQLException;

/**
 * 业务相关，封装DAO层通用代码
 * 不会关闭connection！！！
 */
public class QueryRunnerProxy {

    private QueryRunnerProxy() {
        // Exists to defeat instantiation
    }

    private static QueryRunner runner = new QueryRunner();

    public static int update(String sql, Object... param) {
        int updates = 0;
        try {
            updates = runner.update(ConnectionFactory.getConnection(), sql, param);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return updates;
    }

    public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
        T result = null;
        try {
            result = runner.query(ConnectionFactory.getConnection(), sql, rsh, params);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
