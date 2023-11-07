package com.example.mqq.exception;

import com.example.mqq.utils.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTokenException extends RuntimeException{
    private Integer code;
    private String msg;

    @ExceptionHandler(MyTokenException.class)
    @ResponseBody
    public Object error(MyTokenException e){
        e.printStackTrace();
        return Result.error(e.getCode(),e.getMsg());
    }
}
