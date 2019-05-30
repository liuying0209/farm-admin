package com.farm.admin.admin;

import java.io.Serializable;

/**
 ** @Date: 2019-04-22 00:36
 */
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 457710294715591355L;


    private String username ;

    private String password;

    private String rememberMe;


    public LoginVO() {
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

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
