package com.farm.admin.admin.service.impl;

import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.AdminVO;
import com.farm.admin.admin.mapper.AdminMapper;
import com.farm.admin.admin.service.AdminService;
import com.farm.admin.util.PasswordUtils;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 ** @Date: 2019-04-26 21:49
 */
@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    private static final String DEFAULT_PASSWORD = "123456";
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Page pagingAdmin(Integer pageNo, Integer pageSize, String username) throws AdminException {
        if (pageNo == null || pageSize == null) {
            LOGGER.error("必要参数为空 pageNo :{}, pageSize:{}", pageNo, pageSize);
            throw new AdminException(AdminException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }

        Page page = new Page(pageNo, pageSize);

        int totalRecord = this.adminMapper.countAdmin(username);
        LOGGER.info("查询总数是 ：{}", totalRecord);
        page.setTotalCount(totalRecord);
        if (totalRecord > 0) {
            List<Map<String, Object>> mapList = this.adminMapper.pagingAdmin(page.getPageStart(), page.getPageSize(), username);
            page.setData(mapList);
        }
        return page;
    }

    @Override
    public void saveAdmin(AdminVO params) throws AdminException {
        String username = params.getUsername();
        String mobile = params.getMobile();
        String email = params.getEmail();
        String levelId = params.getLevelId();
        String parentId = params.getParentId();
        String permissionIds = params.getPermissionIds();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(mobile)) {
            LOGGER.error("添加用户失败 必要参数为空 username :{},mobile:{}", username, mobile);
            throw new AdminException(AdminException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setLevelId("2");
        admin.setMobile(mobile);
        admin.setPassword(PasswordUtils.digest(DEFAULT_PASSWORD));
        this.adminMapper.insert(admin);

    }

    @Override
    public void resetPasswords(Long adminId) throws BaseException {

        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setPassword(PasswordUtils.digest(DEFAULT_PASSWORD));
        this.adminMapper.update(admin);

    }

    @Override
    public void updatePassword(String password, String newPassword, Long adminId) throws AdminException {

        Admin currentAdmin = this.adminMapper.findCurrentAdmin(adminId);

        if (currentAdmin == null) {

            LOGGER.error("用户名 有误 adminId :{}", adminId);
            throw new AdminException(AdminException.ERROR_CODE_NOT_EXIST_USER);

        }
        String digestPassword = PasswordUtils.digest(password);
        String adminPassword = currentAdmin.getPassword();

        if (adminPassword.equals(digestPassword)) {

            currentAdmin.setPassword(PasswordUtils.digest(newPassword));
            this.adminMapper.update(currentAdmin);
            LOGGER.info("密码修改成功");
        } else {
            LOGGER.error("原始密码有误");
            throw new AdminException(AdminException.PASS_IS_ERROR);

        }
    }

    @Override
    public void updateAdmin(AdminVO params) throws AdminException {
        Long adminId = params.getAdminId();
        String username = params.getUsername();
        String mobile = params.getMobile();
        String email = params.getEmail();
        String levelId = params.getLevelId();
        String password = params.getPassword();
        String parentId = params.getParentId();
        String permissionIds = params.getPermissionIds();

        if (adminId == null) {
            LOGGER.error("修改用户失败  必要参数 adminId 为空");
            throw new AdminException(AdminException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }

        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setLevelId("2");
        admin.setPassword(PasswordUtils.digest(password));

        this.adminMapper.update(admin);
    }

    @Override
    public void deleteAdmin(Long adminId) throws AdminException {
        if (adminId == null) {
            LOGGER.error("修改用户失败  必要参数 adminId 为空");
            throw new AdminException(AdminException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        this.adminMapper.delete(adminId);
    }

    @Override
    public Map<String, Object> getAdminDetail(Long adminId) throws AdminException {

        if (adminId == null) {
            LOGGER.error("修改用户失败  必要参数 adminId 为空");
            throw new AdminException(AdminException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Map<String, Object> map = this.adminMapper.findById(adminId);
        return map;


    }
}
