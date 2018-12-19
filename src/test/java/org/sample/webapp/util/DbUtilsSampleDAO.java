package org.sample.webapp.util;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.sample.webapp.db.connmanager.ConnectionFactory;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.*;

public class DbUtilsSampleDAO {

    private AsyncQueryRunner runner = new AsyncQueryRunner(Executors.newCachedThreadPool());

    public Future<Integer> saveProfile(Profile profile) {
        Future<Integer> future;
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            future = runner.update(conn,
                    "INSERT ignore INTO `profiles`.`profile` (`username`, `password`, `nickname`) " +
                            "VALUES (?, ?, ?)", profile.getUsername(), profile.getPassword(), profile.getNickname());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return future;
    }

    public static void main(String[] args) {
        DbUtilsSampleDAO dao = new DbUtilsSampleDAO();
        Profile profile = new Profile("myusername", "mypassword", "thisnickname");
        Future<Integer> future = dao.saveProfile(profile);
        try {
            System.out.println(future.get() == 1 ? "更新成功" : "更新失败");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
