package com.farm.admin.admin;

import java.io.Serializable;
import java.util.Date;


public class Admin implements Serializable{

    private static final long serialVersionUID = 6219208354698504206L;
    //0:禁止登录
    public static final int DISABLE = 0;
    //2:用户被删除
    public static final int DELETE = 2;

    //没有修改过初始密码
    public static final boolean IS_PASSWORD = false;

    //默认密码
    public static final String DEFAULT_PASSWORD = "123456abc";

    private Long id;
    private String levelId;
    private Long parentId;
    private String username;
    private String password;
    private boolean isPassword;
    private String email;
    private String mobile;
    private int status;
    private boolean isAdministrator;
    private Date createDate;
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsPassword() {
        return isPassword;
    }

    public void setIsPassword(boolean isPassword) {
        this.isPassword = isPassword;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
