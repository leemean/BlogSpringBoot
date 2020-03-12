package com.liming.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.liming.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString(callSuper = false)
@Table(name = "user")
public class User extends BaseEntity<Long> {
    private static final long serialVersionUID = -4454737765850239378L;

    @NotNull
    @NotBlank
    @Column(unique = true,length = 64)
    private String account;

    /**
     * 使用md5(username + original password + salt)加密存储
     */
    @NotNull
    @NotBlank
    @Column(length = 64)
    private String password;

    @Column(length = 255)
    private String avatar;

    @Column(unique = true, length = 32)
    private String email;

    @NotBlank
    @Column(length = 64)
    private String nickname;

    @Column(length = 20)
    private String mobile;

    /**
     * 加密密码时使用的盐值
     */
    private String salt;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 最后一次登录时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    /**
     * 系统用户的状态
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.normal;

    /**
     * 是否是管理员
     */
    private Boolean admin = false;


    /**
     * 逻辑删除标识
     */
    private Boolean deleted = Boolean.FALSE;
}
