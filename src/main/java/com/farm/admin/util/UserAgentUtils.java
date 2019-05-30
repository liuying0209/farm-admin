package com.farm.admin.util;


import com.farm.admin.admin.UserAgent;


public class UserAgentUtils {
    private static final ThreadLocal<UserAgent> LOCAL_USER_AGENT = new ThreadLocal<>();

    public static UserAgent getCurrent() {
        return LOCAL_USER_AGENT.get();
    }

    public static void setUserAgent(UserAgent userAgent) {
        LOCAL_USER_AGENT.set(userAgent);
    }

    public static void reset() {
        LOCAL_USER_AGENT.remove();
    }
}
