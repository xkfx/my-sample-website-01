package org.sample.dao;

import org.sample.entity.Profile;
import org.sample.exception.DaoException;

import java.util.List;

public interface ProfileDAO {

    int saveProfile(Profile profile);

    List<Profile> listProfileByNickname(String nickname);

    Profile getProfileByUsername(String username);

    int updateProfileById(Profile profile);

    int updatePassword(String username, String password);

    int updateLastOnline(String username);
}

