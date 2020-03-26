package com.google.mapper;

import com.google.domain.ArcType;
import com.google.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ArcTypeMapper {
    int deleteByPrimaryKey(Integer arcTypeId);

    int insert(ArcType record);

    int insertSelective(ArcType record);

    ArcType selectByPrimaryKey(Integer arcTypeId);

    int updateByPrimaryKeySelective(ArcType record);

    int updateByPrimaryKey(ArcType record);

    List<ArcType>findAll(String name);

    List<ArcType>selectAll();




}