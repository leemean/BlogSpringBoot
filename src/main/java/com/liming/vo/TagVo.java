package com.liming.vo;

import com.liming.entity.Tag;
import lombok.Getter;
import lombok.Setter;

public class TagVo extends Tag {
    private static final long serialVersionUID = 5059212992497947120L;

    @Getter
    @Setter
    private int articles;
}
