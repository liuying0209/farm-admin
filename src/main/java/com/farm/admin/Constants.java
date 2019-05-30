/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin;

public final class Constants {

    public static final String COOKIE_NAME_SESSION_ID = "farm_session_id";
    public static final String PARAMETER_DIGEST = "farm_digest";
    public static final String SESSION_USER_ID = "farm_session_user_id";
    public static final String SESSION_USER_NAME = "farm_session_user_name";

    public static final String DEPT_ID_REGULAR = "[a-zA-Z0-9]+$";
    public static final String EMAIL_REGULAR = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    public static final String MOBILE_REGULAR = "^1\\d{10}";

}
