package com.liming.common.result;

import com.liming.common.constant.ResultCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = -4762928619495260423L;

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(ResultCode resultCode){
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result error(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public Map<String, Object> simple() {
        Map<String, Object> simple = new HashMap<String, Object>();
        this.data = simple;
        return simple;
    }

    public void setResultCode(ResultCode code){
        this.code = code.code();
        this.msg = code.message();
    }

}
