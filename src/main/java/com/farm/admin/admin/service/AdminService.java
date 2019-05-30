package com.farm.admin.admin.service;

import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.AdminVO;
import com.farm.base.BaseException;
import com.farm.base.common.Page;

import java.util.Map;

/**
 ** @Date: 2019-04-26 21:49
 */
public interface AdminService {

    /**
     * 后台管理员列表
     */
    Page pagingAdmin(Integer pageNo, Integer pageSize, String username) throws AdminException;


    /**
     * 添加后台管理员
     */
    void saveAdmin(AdminVO params) throws AdminException;

    /**
     * 重置密码
     */
    void resetPasswords(Long adminId) throws BaseException;

    /**
     * 修改密码
     */

    void updatePassword(String password, String newPassword, Long adminId) throws AdminException;

    /**
     * 修改后台管理员
     */

    void updateAdmin(AdminVO params) throws AdminException;

    /**
     * 删除后台管理员
     */
    void deleteAdmin(Long adminId) throws AdminException;

    /**
     * 后台管理员详情
     */

    Map<String, Object> getAdminDetail(Long adminId) throws AdminException;

}
