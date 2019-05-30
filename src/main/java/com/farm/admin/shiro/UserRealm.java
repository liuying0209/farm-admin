package com.farm.admin.shiro;


import com.farm.admin.admin.Admin;
import com.farm.admin.admin.mapper.AdminMapper;
import com.farm.admin.admin.mapper.PermissionMapper;
import com.farm.admin.admin.mapper.RolePermissionMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public UserRealm() {
        super();
    }

    /**
     * 认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {


        UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());
        Admin admin = adminMapper.login(mobile, password);
        if (null == admin) {
            throw new AccountException("帐号或密码不正确！");
        }else if(admin.getStatus() == Admin.DELETE){
            throw new AccountException("帐号被删除！");
        }else if(admin.getStatus() == Admin.DISABLE){
            throw new AccountException("帐号被禁用！");
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Admin token = TokenManager.getToken();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Long adminId = token.getId();
        Set<String> roles = new LinkedHashSet<>();
        Set<String> permissions = new LinkedHashSet<>();
        //用户是admin 给所有的权限
        if (token.isAdministrator()) {
            roles.add("admin");
            permissions = rolePermissionMapper.findAll();
        } else {
            roles.add(adminId.toString());
            permissions = rolePermissionMapper.findPermissionByUserId(adminId);
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 指定principalCollection 清除
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }


}
