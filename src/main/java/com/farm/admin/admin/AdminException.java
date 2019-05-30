/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin.admin;


import com.farm.base.BaseException;


public class AdminException extends BaseException {

    private static final long serialVersionUID = -7605431395388634159L;

    public static final int ERROR_CODE_USER_DELETED = 700;
    public static final int ERROR_CODE_LOGIN_OUT_FAILED = 702;
    public static final int ERROR_CODE_LOGIN_FAILED = 701;
    public static final int ERROR_CODE_LOGIN_DISABLE = 703;
    public static final int ERROR_CODE_LOGIN_NOT_RESET_PASSWORD = 704;
    public static final int ERROR_CODE_NOT_BE_NULL = 710;
    public static final int ERROR_CODE_NOT_UPDATE_SUPERIOR = 711;
    public static final int ERROR_CODE_DEPT_ID_OR_NAME_EXIST = 712;
    public static final int ERROR_CODE_MOBILE_EXIST = 713;
    public static final int ERROR_CODE_EMAIL_EXIST = 714;
    public static final int ERROR_CODE_IS_OLD_PASSWORD = 715;
    public static final int ERROR_CODE_EMAIL_FORMAT = 716;
    public static final int ERROR_CODE_MOBILE_FORMAT = 717;
    public static final int ERROR_CODE_NOT_EXIST_DEPT = 719;
    public static final int ERROR_CODE_NOT_EXIST_USER = 720;
    public static final int ERROR_CODE_NOT_OLD_PASSWORD = 721;
    public static final int ERROR_CODE_NOT_PARENT = 722;
    public static final int PASS_IS_ERROR = 723;
    public static final int ERROR_CODE_DEPT_ID_FORMAT_ERROR = 730;
    /**
     * 拷贝只读库中用户至CRM时,可能用户已存在CRM。抛出次异常。
     */
    public static final int ERROR_CODE_USER_ALREADY_EXISTS = 731;


    public AdminException(int errorCode) {
        super(errorCode);
    }
}
