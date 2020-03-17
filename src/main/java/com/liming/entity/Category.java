package com.liming.entity;

import com.liming.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5025313969040054182L;

    @NotBlank
    @Column(name = "category_name")
    private String categoryName;

    private String description;

//    @NotBlank
//    private String avatar;
}
