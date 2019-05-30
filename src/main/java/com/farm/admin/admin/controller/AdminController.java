package com.farm.admin.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.AdminVO;
import com.farm.admin.admin.service.AdminService;
import com.farm.admin.util.AdminUtil;
import com.farm.base.BaseException;
import com.farm.base.common.JsonResult;
import com.farm.base.common.JsonResultUtils;
import com.farm.base.common.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("add")
    public JsonResult add(@RequestBody AdminVO params) throws AdminException {
        LOGGER.info("请求报告: 添加管理员");
        adminService.saveAdmin(params);
        return JsonResult.ok();
    }


    @PostMapping("reset")
    public JsonResult reset(@RequestBody JSONObject object) throws BaseException {
        LOGGER.info("请求报告: 重置密码");
        Object adminId = object.get("adminId");
        if (adminId == null) {
            LOGGER.info("必要参数为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Long id = Long.valueOf(adminId.toString());
        adminService.resetPasswords(id);
        return JsonResult.ok();
    }

    @PostMapping("updatePassword")
    public JsonResult updatePassword(@RequestBody JSONObject object) throws BaseException {
        LOGGER.info("请求报告: 修改密码");
        Object password = object.get("password");
        Object newPassword = object.get("newPassword");

        Admin admin = AdminUtil.getAdmin();
        Long adminId = admin.getId();
        if (adminId == null) {
            LOGGER.error("必要参数为空 {}", adminId);
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);

        }

        this.adminService.updatePassword(password.toString(), newPassword.toString(), adminId);

        return JsonResult.ok();
    }


    @GetMapping("page")
    public JsonResult pageAdmin(@RequestParam("pageNo") Integer pageNo,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "username", required = false) String usernmae
    ) throws AdminException {
        LOGGER.info("请求报告: 管理员列表");
        Page page = this.adminService.pagingAdmin(pageNo, pageSize, usernmae);
        return JsonResultUtils.getResultByPage(page);
    }

    @GetMapping("{adminId}")
    public JsonResult detail(@PathVariable("adminId") Long adminId) throws AdminException {
        LOGGER.info("请求报告: 管理详情");
        Map<String, Object> map = this.adminService.getAdminDetail(adminId);
        return JsonResult.ok(map);
    }

    @PutMapping("update")
    public JsonResult update(@RequestBody AdminVO params) throws AdminException {
        LOGGER.info("请求报告: 更新管理员");
        adminService.updateAdmin(params);
        return JsonResult.ok();
    }

    @DeleteMapping("{adminId}")
    public JsonResult delete(@PathVariable("adminId") Long adminId) throws AdminException {
        LOGGER.info("请求报告: 删除管理员");
        this.adminService.deleteAdmin(adminId);
        return JsonResult.ok();
    }

}
