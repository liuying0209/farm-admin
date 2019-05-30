package com.farm.admin.admin;

import java.io.Serializable;

/**
 * 管理员相关参数接收类
 *
 ** @Date: 2019-04-26 22:27
 */
public class AdminVO implements Serializable {
    private static final long serialVersionUID = 8092029992258844690L;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 父admin节点
     */
    private String parentId;

    /**
     * 员工职位表编号
     */
    private String levelId;
    /**
     * 权限编号集
     */
    private String permissionIds;

    public AdminVO() {
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
