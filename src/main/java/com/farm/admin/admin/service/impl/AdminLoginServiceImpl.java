/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin.admin.service.impl;

import com.farm.admin.Constants;
import com.farm.admin.shiro.TokenManager;
import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.LoginVO;
import com.farm.admin.admin.mapper.AdminMapper;
import com.farm.admin.admin.service.AdminLoginService;

import com.farm.admin.util.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginServiceImpl.class);
    private static final long DAYS_10_IN_MILLIS = 10L * 24 * 60 * 60 * 1000;

    private final AdminMapper userMapper;

    @SuppressWarnings("all")
    @Autowired
    public AdminLoginServiceImpl(AdminMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Admin login(LoginVO loginVO) throws AdminException {
        String mobile = loginVO.getUsername();
        String password = loginVO.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(mobile, PasswordUtils.digest(password));
        if (StringUtils.isNotBlank(loginVO.getRememberMe())) {
            Boolean flag = Boolean.valueOf(loginVO.getRememberMe());
            token.setRememberMe(flag);
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            LOGGER.error("Login Failed... mobile = " + mobile);
            throw new AdminException(AdminException.ERROR_CODE_LOGIN_FAILED);
        }
        final Admin user = (Admin) currentUser.getPrincipal();
        if (Admin.IS_PASSWORD == user.getIsPassword()) {
            throw new AdminException(AdminException.ERROR_CODE_LOGIN_NOT_RESET_PASSWORD);
        }
        if (Admin.DISABLE == user.getStatus()) {
            throw new AdminException(AdminException.ERROR_CODE_LOGIN_DISABLE);
        }

        final Session session = currentUser.getSession();
        session.setTimeout(DAYS_10_IN_MILLIS);
        session.setAttribute(Constants.SESSION_USER_ID, user.getId());
        session.setAttribute(Constants.SESSION_USER_NAME, user.getUsername());
        return user;
    }

    @Override
    public boolean loginOut() throws AdminException {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (AuthenticationException e) {
            throw new AdminException(AdminException.ERROR_CODE_LOGIN_OUT_FAILED);
        }
        return currentUser.isAuthenticated();
    }

    @Override
    public Map<String, Object> getUserInfo() {
        return userMapper.findById(TokenManager.getUserId());
    }
}
