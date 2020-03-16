package com.liming.entity;

import com.liming.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 文章内容
 */
@Getter
@Setter
@Entity
@Table(name = "article_body")
public class ArticleBody extends BaseEntity<Long> {
    private static final long serialVersionUID = -7611409995977927628L;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String content;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String content_html;
}
