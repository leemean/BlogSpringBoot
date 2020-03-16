package com.liming.vo;

import com.liming.entity.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleVo extends Article {
    private Integer year;

    private Integer month;

    private Integer tagId;

    private Integer categoryId;

    private Integer count;
}
