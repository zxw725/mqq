package com.example.mqq.exception;

import com.example.mqq.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class myException{

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object Nullerror(NullPointerException e){
        e.printStackTrace();
        return Result.error(500,"空指针异常");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object IllegalArgumentExceptionError(IllegalArgumentException e){
        e.printStackTrace();
        return Result.error(499,"token无效,请重新登入");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object ExceptionError(Exception e){
        e.printStackTrace();
        return Result.error(501,e.getMessage());
    }


}
