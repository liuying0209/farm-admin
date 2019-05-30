/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with htouhui.com.
 */
package com.farm.admin;

import com.farm.admin.shiro.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import static com.farm.admin.Constants.COOKIE_NAME_SESSION_ID;


/**
 * Shiro配置
 *
 */
@Configuration
public class ShiroConfigure {


    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:shiro/ehcache-shiro.xml");
        return em;
    }


    @Bean(name = "userRealm")
    public UserRealm getUserRealm(EhCacheManager ehCacheManager) {
        UserRealm realm = new UserRealm();
        realm.setCacheManager(ehCacheManager);
        return realm;
    }


    @Bean
    public FilterRegistrationBean createShiroFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("shiroFilter");
        filterRegistrationBean.setFilter(new DelegatingFilterProxy());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        SimpleCookie sessionIdCookie = new SimpleCookie(COOKIE_NAME_SESSION_ID);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setDeleteInvalidSessions(true);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/index.html");//未登录跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/index.html");//未授权跳转

        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持
     * 拦截@ RequiresPermissions注释的方法
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
