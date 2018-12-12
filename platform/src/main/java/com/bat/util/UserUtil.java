package com.bat.util;

import com.bat.domain.we.MiaoShaUser;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-12 19:01
 **/
public class UserUtil {
	private static ThreadLocal<MiaoShaUser> userLoacl = new ThreadLocal<>();

	public static MiaoShaUser getUser() {
		return userLoacl.get();
	}

	public static void setUser(MiaoShaUser user) {
		userLoacl.set(user);
	}
}
