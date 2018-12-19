package org.sample.webapp.entity;

import java.sql.Timestamp;

public class Profile {

    private long id;
    private String username;
    private String password;
    private String nickname;
    private Timestamp lastOnline;
    private Character gender;
    private Timestamp birthday;
    private String location;
    private Timestamp joined;

    public Profile() {
    }

    public Profile(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Timestamp lastOnline) {
        this.lastOnline = lastOnline;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getJoined() {
        return joined;
    }

    public void setJoined(Timestamp joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", lastOnline=" + lastOnline +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", location='" + location + '\'' +
                ", joined=" + joined +
                '}';
    }
}
