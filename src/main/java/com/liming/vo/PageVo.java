package com.liming.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVo {
    private Integer pageNumber;

    private Integer pageSize;

    private String name;

    private String sort;
}
