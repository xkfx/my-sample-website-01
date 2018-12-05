package org.sample.webapp.dao.impl;

import org.sample.webapp.dao.ProfileDAO;
import org.sample.webapp.db.ConnectionFactory;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.exception.DaoException;
import org.sample.webapp.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl implements ProfileDAO {

    public static final ProfileDAO INSTANCE = new ProfileDAOImpl();

    private ProfileDAOImpl() {}

    @Override
    public int saveProfile(Profile profile) {
        int i = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT ignore INTO `profiles`.`profile` (`username`, `password`, `nickname`) " +
                    "VALUES (?, ?, ?)"; // 添加ignore出现重复不会抛出异常而是返回0
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, profile.getUsername());
                ps.setString(2, profile.getPassword());
                ps.setString(3, profile.getNickname());
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i;
    }

    @Override
    public List<Profile> listByNickname(String nickname) {
        List<Profile> result = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "SELECT  `profile_id`,  `username`,  `password`,  `nickname`,  `last_online`,  `gender`,  `birthday`,  `location`,  `joined`" +
                    "FROM `profiles`.`profile`" +
                    "WHERE `nickname`=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nickname);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Profile profile = DbUtil.extractProfileFromResultSet(rs);
                        result.add(profile);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Profile getByUsername(String username) {
        Profile result = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "SELECT  `profile_id`,  `username`,  `password`,  `nickname`,  `last_online`,  `gender`,  `birthday`,  `location`,  `joined`" +
                    "FROM `profiles`.`profile`" +
                    "WHERE `username`=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result = DbUtil.extractProfileFromResultSet(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public int updateById(Profile profile) {
        int i = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "UPDATE `profiles`.`profile`" +
                    "SET `nickname`=?,  `gender`=?,  `birthday`=?,  `location`=? " +
                    "WHERE  `profile_id`=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, profile.getNickname());
                ps.setString(2, profile.getGender() != null ? String.valueOf(profile.getGender()) : null);
                ps.setTimestamp(3, profile.getBirthday());
                ps.setString(4, profile.getLocation());
                ps.setLong(5, profile.getId());
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i;
    }

    @Override
    public int updatePassword(String username, String password) {
        int i = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "UPDATE `profiles`.`profile`" +
                    "SET `password`=? " +
                    "WHERE  `username`=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, password);
                ps.setString(2, username);
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i;
    }

    @Override
    public int updateLastOnline(String username) {
        int i = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "UPDATE `profiles`.`profile`" +
                    "SET `last_online`=CURRENT_TIMESTAMP " +
                    "WHERE  `username`=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return i;
    }
}
