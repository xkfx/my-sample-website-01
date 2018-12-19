package org.sample.webapp.util;

import org.sample.webapp.entity.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

    public static Profile extractProfileFromResultSet(ResultSet rs) throws SQLException {
        Profile profile = new Profile();
        profile.setBirthday(rs.getTimestamp("birthday"));
        profile.setJoined(rs.getTimestamp("joined"));
        profile.setLastOnline(rs.getTimestamp("last_online"));
        profile.setLocation(rs.getString("location"));
        profile.setNickname(rs.getString("nickname"));
        profile.setPassword(rs.getString("password"));
        profile.setId(rs.getLong("profile_id"));
        profile.setUsername(rs.getString("username"));
        if (rs.getString("gender") != null) {
            profile.setGender(rs.getString("gender").charAt(0));
        }
        return profile;
    }
}
