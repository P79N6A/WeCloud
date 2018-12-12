package com.bat.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-12 17:08
 **/
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessCtrl {
	boolean needLogin() default true;
}
