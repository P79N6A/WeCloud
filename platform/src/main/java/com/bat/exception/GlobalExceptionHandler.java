package com.bat.exception;

import com.bat.result.CodeMsg;
import com.bat.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){

        e.printStackTrace();
        if(e instanceof Exception){
            return Result.error(500,e.getMessage());
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
