package com.google.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.annotations.Many;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;

@Data
public class Article implements Serializable {
    /**
     * 资源id
     */
    private Integer articleId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 资源类型id
     */
    private Integer arcTypeId;

    /**
     * 是否免费资源
     */
    private Boolean isFree;

    /**
     * 积分
     */
    private Integer points=0;

    /**
     * 内容
     */
    private String content;

    /**
     * 下载地址
     */
    private String download;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否热门资源
     */
    private Boolean isHot = false;

    /**
     * 状态：1未审核2审核通过3审核驳回
     */
    private Integer state=1;

    /**
     * 驳回原因
     */
    private String reason;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    /**
     * 点击数
     */
    private Integer click;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 描述
     */
    private String description;

    /**
     * 资源链接是否有效
     */
    private Boolean isUseful = true;

    @Transient
    private String publishDateStr;//发布时间字符串


    private User user;  //所属用户

    private ArcType arcType; //所属资源类型

    @Transient
    private String contentNoTag;

    private static final long serialVersionUID = 1L;
}