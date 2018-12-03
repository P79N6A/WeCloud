package com.bat.result;

import lombok.Getter;

@Getter
public class CodeMsg {
    private Integer code;
    private String msg ;

    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500,"服务端异常");
    public static CodeMsg USERNAME_NOT_FOUND = new CodeMsg(500,"用户名没找到");
    public static CodeMsg PASSWORD_IS_WRONG = new CodeMsg(500,"用户名没找到");


    private CodeMsg(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
