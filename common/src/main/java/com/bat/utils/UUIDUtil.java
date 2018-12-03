package com.bat.utils;

import java.util.UUID;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-03 16:26
 **/
public class UUIDUtil {
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
}
