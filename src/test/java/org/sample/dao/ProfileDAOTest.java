package org.sample.dao;

import org.junit.Test;
import org.sample.dao.impl.ProfileDAOImpl;
import org.sample.entity.Profile;
import org.sample.manager.ConnectionProxy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProfileDAOTest {

    private static final ProfileDAO PROFILE_DAO = ProfileDAOImpl.INSTANCE;

    private static final String ORIGIN_STRING = "hello";
    private static final String PASSWORD = ORIGIN_STRING;

    private static String RandomString() {
        return Math.random() + ORIGIN_STRING + Math.random();
    }

    private static Profile RandomProfile() {
        Profile profile = new Profile(RandomString(), PASSWORD, RandomString());
        return profile;
    }

    @Test
    public void saveProfile() throws Exception {
        Profile profile = RandomProfile();
        int i = PROFILE_DAO.saveProfile(profile);
        int j = PROFILE_DAO.saveProfile(profile);
        ConnectionProxy.close();

        assertEquals(1, i);
        assertEquals(0, j);
    }

    @Test
    public void listProfileByNickname() throws Exception {
        final String nickName = RandomString();
        Profile profile1 = new Profile(RandomString(), PASSWORD, nickName);
        Profile profile2 = new Profile(RandomString(), PASSWORD, nickName);
        Profile profile3 = new Profile(RandomString(), PASSWORD, nickName);
        PROFILE_DAO.saveProfile(profile1);
        PROFILE_DAO.saveProfile(profile2);
        PROFILE_DAO.saveProfile(profile3);
        List result = PROFILE_DAO.listByNickname(nickName);
        ConnectionProxy.close();

        assertEquals(3, result.size());
    }

    @Test
    public void getProfileByUsername() throws Exception {
        Profile profile = RandomProfile();
        PROFILE_DAO.saveProfile(profile);
        Profile result = PROFILE_DAO.getByUsername(profile.getUsername());
        ConnectionProxy.close();

        assertNotNull(result);
    }

    @Test
    public void updateProfileById() throws Exception {
        Profile profile = RandomProfile();
        PROFILE_DAO.saveProfile(profile);
        Profile temp = PROFILE_DAO.getByUsername(profile.getUsername());
        int i = PROFILE_DAO.updateById(temp);
        ConnectionProxy.close();

        assertEquals(1, i);
    }

    @Test
    public void updatePassword() throws Exception {
        Profile profile = RandomProfile();
        PROFILE_DAO.saveProfile(profile);
        int i = PROFILE_DAO.updatePassword(profile.getUsername(), RandomString());
        ConnectionProxy.close();

        assertEquals(1, i);
    }

    @Test
    public void updateLastOnline() throws Exception {
        Profile profile = RandomProfile();
        PROFILE_DAO.saveProfile(profile);
        int i = PROFILE_DAO.updateLastOnline(profile.getUsername());
        ConnectionProxy.close();

        assertEquals(1, i);
    }

}