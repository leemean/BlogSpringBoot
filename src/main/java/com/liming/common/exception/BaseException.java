package com.liming.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3506744187536228284L;

    @Getter
    @Setter
    private String errCode;

    @Getter
    @Setter
    private String errMsg;

    public BaseException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}
