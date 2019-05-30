/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with haitouhui.com.
 */
package com.farm.admin.web;


import com.farm.base.BaseException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleInterceptor extends HandlerInterceptorAdapter {

    private List<String> skipCheckURIs = new ArrayList<>();

    public UserRoleInterceptor() {
        skipCheckURIs.add("/api/login");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String abc= request.getContextPath() + "/api/login";

        List<String> uris = skipCheckURIs.stream().filter(s -> request.getRequestURI().startsWith(request.getContextPath() + s)).collect(Collectors.toList());
        if (uris.size() > 0) { // skip check uri
            return true;
        }
        final Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new BaseException(BaseException.ERROR_CODE_NO_AUTHENTICATED);
        }
        request.setAttribute("subject", subject);
        return true;
    }

}
