/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin.util;

import org.apache.commons.codec.digest.DigestUtils;


public class PasswordUtils {

    public static final String SALT = "+HTOUHUI2016+";

    public static String digest(String password) {
        return DigestUtils.md5Hex(SALT + password + SALT);
    }

}
