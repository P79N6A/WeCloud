package com.bat.result;

import lombok.Getter;

//@Getter
public class Result<T> {
    private Integer code ;
    private String msg ;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> error(Integer code,String msg){
        return new Result<T>(code,msg);
    }

    public static Result error(CodeMsg codeMsg){
        if(codeMsg != null) {
            return new Result(codeMsg.getCode(), codeMsg.getMsg());
        }else{
            return error(CodeMsg.SERVER_ERROR);
        }
    }


    private Result(T data){
        this(0,"success",data);
    }

    private Result(Integer code,String msg){
        this(code,msg,null);
    }

    private Result(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
