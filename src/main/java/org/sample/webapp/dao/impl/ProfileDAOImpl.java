package org.sample.webapp.dao.impl;

import org.sample.webapp.dao.ProfileDAO;
import org.sample.webapp.db.connmanager.ConnectionFactory;
import org.sample.webapp.db.queryrunner.QueryRunnerProxy;
import org.sample.webapp.db.queryrunner.RsHandlers;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 该类方法统一抛出DaoException
 */
public enum ProfileDAOImpl implements ProfileDAO {

    INSTANCE;

    @Override
    public int saveProfile(Profile profile) {
        final String sql = "INSERT ignore INTO profile (username, password, nickname) " +
                "VALUES (?, ?, ?)"; // 添加ignore出现重复不会抛出异常而是返回0
        return QueryRunnerProxy.update(sql, profile.getUsername(), profile.getPassword(), profile.getNickname());
    }

    @Override
    public List<Profile> listByNickname(String nickname) {
        final String sql = "SELECT  profile_id AS id,  username,  password,  nickname, last_online AS lastOnline, gender, birthday, location, joined " +
                "FROM profile " +
                "WHERE nickname=?";
        return QueryRunnerProxy.query(sql, RsHandlers.PROFILE_LIST, nickname);
    }

    @Override
    public Profile getByUsername(String username) {
        final String sql = "SELECT  profile_id AS id,  username,  password,  nickname, last_online AS lastOnline, gender, birthday, location, joined " +
                "FROM profile " +
                "WHERE username=?"; // TODO 该字符串会反复创建吗？
        return QueryRunnerProxy.query(sql, RsHandlers.PROFILE, username);
    }

    @Override
    public int updateById(Profile profile) {
        final String sql = "UPDATE profile " +
                "SET nickname=?, gender=?, birthday=?, location=? " +
                "WHERE profile_id=?";
        return QueryRunnerProxy.update(sql, profile.getNickname(), profile.getGender() != null ? String.valueOf(profile.getGender()) : null,
                profile.getBirthday(), profile.getLocation(), profile.getId());
    }

    @Override
    public int updatePassword(String username, String password) {
        final String sql = "UPDATE profile " +
                "SET password=? " +
                "WHERE username=?";
        return QueryRunnerProxy.update(sql, password, username);
    }

    @Override
    public int updateLastOnline(String username) {
        final String sql = "UPDATE profile " +
                "SET last_online=CURRENT_TIMESTAMP " +
                "WHERE username=?";
        return QueryRunnerProxy.update(sql, username);
    }
}
