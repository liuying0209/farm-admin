package com.farm.admin.admin;

import java.io.Serializable;


public class UserAgent implements Serializable {
    private static final long serialVersionUID = 7894938254063831917L;

    public UserAgent() {

    }

    public UserAgent(String userAgent, String ip) {
        this.userAgent = userAgent;
        this.ip = ip;
    }

    private String userAgent;
    private String ip;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
