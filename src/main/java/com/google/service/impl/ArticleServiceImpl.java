package com.google.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.google.mapper.ArticleMapper;
import com.google.domain.Article;
import com.google.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Integer articleId) {
        return articleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int insert(Article record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(Article record) {
        return articleMapper.insertSelective(record);
    }

    @Override
    public Article selectByPrimaryKey(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Article record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

}
