package com.google.service;

import com.github.pagehelper.PageInfo;
import com.google.domain.ArcType;
import com.google.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

public interface ArcTypeService{


    int deleteByPrimaryKey(Integer arcTypeId);

    int insert(ArcType record);

    int insertSelective(ArcType record);

    ArcType selectByPrimaryKey(Integer arcTypeId);

    int updateByPrimaryKeySelective(ArcType record);

    int updateByPrimaryKey(ArcType record);

    PageInfo<ArcType> findAll(String name, int page, int pageSize);

    List<ArcType>selectAll();





}
