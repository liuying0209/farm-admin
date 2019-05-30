package com.farm.admin.shiro;


import com.farm.admin.admin.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class TokenManager {
	/**
	 * 获取当前登录的用户User对象
	 *
	 * @return
	 */
	public static Admin getToken() {
		Admin token = (Admin) SecurityUtils.getSubject().getPrincipal();
		return token;
	}

	/**
	 * 获取当前用户的Session
	 *
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 获取当前用户NAME
	 *
	 * @return
	 */
	public static String getUsername() {
		return  getToken() == null ? null : getToken().getUsername();
	}

	/**
	 * 获取当前用户ID
	 *
	 * @return
	 */
	public static Long getUserId() {
		return getToken() == null ? null : getToken().getId();
	}

	/**
	 * 把值放入到当前登录用户的Session里
	 *
	 * @param key
	 * @param value
	 */
	public static void setSessionVal(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从当前登录用户的Session里取值
	 *
	 * @param key
	 * @return
	 */
	public static Object getSessionVal(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 判断是否登录
	 *
	 * @return
	 */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}
}