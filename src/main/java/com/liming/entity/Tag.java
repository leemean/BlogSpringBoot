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
@Table(name = "tag")
public class Tag extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5025313969040054182L;

    @NotBlank
    @Column(name = "tag_name")
    private String tagName;
}
