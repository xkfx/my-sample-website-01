package com.mycompany.app.dao;

import com.mycompany.app.entity.Profile;

import java.util.List;

public interface ProfileDAO {

    int saveProfile(Profile profile);

    List<Profile> listByNickname(String nickname);

    Profile getByUsername(String username);

    int updateById(Profile profile);

    int updatePassword(String username, String password);

    int updateLastOnline(String username);
}

