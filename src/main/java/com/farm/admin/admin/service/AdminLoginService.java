/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin.admin.service;



import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.LoginVO;

import java.util.Map;

public interface AdminLoginService {

    Admin login(LoginVO loginVO) throws AdminException;

    boolean loginOut() throws AdminException;

    Map<String,Object> getUserInfo();
}
