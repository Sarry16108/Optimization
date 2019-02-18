package com.example.testing.optimization.entity;

/**
 * Created by Administrator on 2017/6/12.
 */

public class UserLoginInfo {
    private String username;
    private String password;
    private long   expires;

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

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }
}
