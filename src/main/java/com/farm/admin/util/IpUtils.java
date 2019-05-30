/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */

package com.farm.admin.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


public final class IpUtils {
    private IpUtils() {
    }

    public static String getIp(HttpServletRequest request) {

        String ip = StringUtils.trimToEmpty(request.getHeader("X-Real-IP"));
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isNotBlank(ip) &&
                    StringUtils.contains(ip, ',')) {
                ip = StringUtils.substringAfterLast(ip, ",");
                ip = StringUtils.trimToEmpty(ip);
            }
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
