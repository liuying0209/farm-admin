package com.farm.admin.util;

import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 ** @Version 1.0.0
 */
public class AdminUtil {

    public static Admin getAdmin() throws AdminException {
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.getPrincipal() == null) {
            throw new  AdminException(AdminException.ERROR_CODE_NOT_EXIST_USER);
        }
        Admin currentAdmin = (Admin) currentUser.getPrincipal();
        return currentAdmin;
    }
}
