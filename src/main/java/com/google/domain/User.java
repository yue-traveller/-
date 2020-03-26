package com.google.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;

@Data
public class User implements Serializable {
    private Integer userId;

    /**
    * qq用户唯一标识
    */
    private String openId;

    /**
    * 昵称
    */

    private String nickname;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String password;

    /**
    * 验证邮箱地址
    */
    private String email;

    /**
    * 用户头像
    */
    private String headPortrait="tou.jpg";

    /**
    * 性别
    */
    private String sex;

    /**
    * 积分
    */
    private Integer points=0;

    /**
    * 是否vip
    */
    private Boolean isVip=false;

    /**
    * vip等级
    */
    private Integer vipGrade=0;

    /**
    * 是否被封禁
    */
    private Boolean isOff=false;

    /**
    * 角色名称：管理员、会员
    */
    private String roleName="普通用户";

    /**
    * 注册时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date registrationDate;

    /**
    * 最近的登陆时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date latelyLoginTime;
    @Transient
    private Integer messageCount;
    private static final long serialVersionUID = 1L;
}