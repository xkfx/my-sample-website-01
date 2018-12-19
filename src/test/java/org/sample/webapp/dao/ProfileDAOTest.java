package org.sample.webapp.dao;

import org.sample.webapp.db.connmanager.ConnectionProxy;
import org.sample.webapp.entity.Profile;
import org.junit.Test;
import org.sample.webapp.dao.impl.ProfileDAOImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProfileDAOTest {

    private final ProfileDAO profileDAO = ProfileDAOImpl.INSTANCE;

    private static final String DEFAULT_STRING = "defaultString";
    private static final String PASSWORD = "password";

    public static String RandomString() {
        return Math.random() + DEFAULT_STRING;
    }

    public static Profile RandomProfile() {
        Profile profile = new Profile(RandomString(), PASSWORD, RandomString());
        return profile;
    }

    @Test
    public void saveProfile() throws Exception {
        Profile profile = RandomProfile();
        int i = profileDAO.saveProfile(profile);
        int j = profileDAO.saveProfile(profile);
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
        profileDAO.saveProfile(profile1);
        profileDAO.saveProfile(profile2);
        profileDAO.saveProfile(profile3);
        List result = profileDAO.listByNickname(nickName);
        System.out.println(result);
        ConnectionProxy.close();

        assertEquals(3, result.size());
    }

    @Test
    public void getProfileByUsername() throws Exception {
        Profile profile = RandomProfile();
        profileDAO.saveProfile(profile);
        Profile result = profileDAO.getByUsername(profile.getUsername());
        ConnectionProxy.close();

        assertNotNull(result);
    }

    @Test
    public void updateProfileById() throws Exception {
        Profile profile = RandomProfile();
        profileDAO.saveProfile(profile);
        Profile temp = profileDAO.getByUsername(profile.getUsername());
        temp.setNickname("看看更新有没效果");
        int i = profileDAO.updateById(temp);
        ConnectionProxy.close();

        assertEquals(1, i);
    }

    @Test
    public void updatePassword() throws Exception {
        Profile profile = RandomProfile();
        profileDAO.saveProfile(profile);
        int i = profileDAO.updatePassword(profile.getUsername(), "this is my new password");
        ConnectionProxy.close();

        assertEquals(1, i);
    }

    @Test
    public void updateLastOnline() throws Exception {
        Profile profile = RandomProfile();
        profileDAO.saveProfile(profile);
        int i = profileDAO.updateLastOnline(profile.getUsername());
        ConnectionProxy.close();

        assertEquals(1, i);
    }
}