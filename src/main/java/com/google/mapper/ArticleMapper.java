package com.google.mapper;

import com.google.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> list(Article article);
}