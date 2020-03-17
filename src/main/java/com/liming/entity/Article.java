package com.liming.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.liming.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "article")
public class Article extends BaseEntity<Long> {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private static final long serialVersionUID = -4470366380115322213L;

    /**
     * 标题
     */
    @NotBlank
    @Column(name = "title",length = 64)
    private String title;

    //@NotBlank
    @Column(name = "summary", length = 255)
    private String summary;

    /**
     * 作者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * 文章主体
     */
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "body_id")
    private ArticleBody body;

    /**
     * 分类 目录
     */
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 标签
     */
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(name = "article_tag",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    /**
     * 评论
     */
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "article",orphanRemoval = true)
    private List<Comment> comments;

    /**
     * 评论数
     */
    @Column(name = "comment_counts")
    private int commentCounts;

    /**
     * 查看次数
     */
    @Column(name = "view_counts")
    private int viewCounts;

    /**
     * 是否置顶
     */
    private int top = Article_Common;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy.MM.dd HH:mm")
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
