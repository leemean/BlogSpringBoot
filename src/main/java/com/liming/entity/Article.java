package com.liming.entity;

import com.liming.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "article")
public class Article extends BaseEntity<Integer> {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private static final long serialVersionUID = -4470366380115322213L;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "title",length = 40)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

}
