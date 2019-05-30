package com.farm.admin.admin.controller;

import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.admin.LoginVO;
import com.farm.admin.admin.service.AdminLoginService;
import com.farm.admin.shiro.TokenManager;
import com.farm.base.common.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("api")
public class LoginController {

    private final AdminLoginService adminLoginService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @SuppressWarnings("all")
    public LoginController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("login")
    public JsonResult doLogin(@RequestBody LoginVO loginVO) throws IOException, AdminException {

        JsonResult result = JsonResult.ok();
        adminLoginService.login(loginVO);
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("token", SecurityUtils.getSubject().getSession().getId());
        Map<String, Object> userInfo = adminLoginService.getUserInfo();
        data.put("info", userInfo);
        result.setData(data);
        return result;
    }

    @GetMapping("logout")
    public JsonResult loginOut() {
        try {
            adminLoginService.loginOut();
        } catch (Exception e) {
            return JsonResult.failed();
        }
        return JsonResult.ok();
    }

    @GetMapping("get_user")
    public JsonResult getUser() throws AdminException {
        JsonResult result = JsonResult.ok();
        Admin admin = TokenManager.getToken();
        if (admin == null) {
            return result;
        }

        Map<String, Object> userInfo = adminLoginService.getUserInfo();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("user_info", userInfo);
        result.setData(data);
        return result;
    }

    @GetMapping("/unlogin")
    public JsonResult unlogin() {
        return JsonResult.ok("fail");

    }

}

