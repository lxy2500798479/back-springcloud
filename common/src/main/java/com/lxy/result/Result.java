package com.lxy.result;

import com.lxy.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MSG = "success";

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String msg){
        Result<T> result = new Result<>();
        result.setCode(String.valueOf(ResultCodeEnum.PARAM_ERROR));
        result.setMsg(msg);
        return result;
    }
}