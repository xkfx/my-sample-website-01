package org.sample.webapp.entity;

import java.sql.Timestamp;

public class Profile {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Timestamp last_online;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Timestamp getLast_online() {
        return last_online;
    }

    public void setLast_online(Timestamp last_online) {
        this.last_online = last_online;
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
                ", last_online=" + last_online +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", location='" + location + '\'' +
                ", joined=" + joined +
                '}';
    }
}
