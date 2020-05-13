package com.liming.common.result;

import lombok.Getter;
import lombok.Setter;

/**
 * Controller参数校验 错误返回封装
 */
@Getter
@Setter
public class ParameterInvalidItem {

    private String fieldName;

    private String message;
}
