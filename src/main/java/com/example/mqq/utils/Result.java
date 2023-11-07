package com.example.mqq.utils;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private Boolean success;
    private T data;
    public Result(){
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

}
