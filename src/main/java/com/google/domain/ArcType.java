package com.google.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ArcType implements Serializable {
    /**
    * 资源类型id
    */
    private Integer arcTypeId;

    /**
    * 资源类型名称
    */
    private String arcTypeName;

    /**
    * 描述
    */
    private String remark;

    /**
    * 排序
    */
    private Integer sort;

    private List<Article>articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    private static final long serialVersionUID = 1L;
}