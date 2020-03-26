package com.google.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.domain.Article;
import com.google.util.Constes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.google.mapper.ArcTypeMapper;
import com.google.domain.ArcType;
import com.google.service.ArcTypeService;

import java.util.List;

@Service
public class ArcTypeServiceImpl implements ArcTypeService{

    @Resource
    private ArcTypeMapper arcTypeMapper;

   @Autowired
    private RedisTemplate<Object,Object> template;

    @Override
    public int deleteByPrimaryKey(Integer arcTypeId) {
        return arcTypeMapper.deleteByPrimaryKey(arcTypeId);
    }

    @Override
    public int insert(ArcType record) {
        boolean flag = false;
        if (record.getArcTypeId()==null) {
            flag = true;

        }
        int insert = arcTypeMapper.insert(record);
        if (flag){
            template.opsForList().rightPush(Constes.ALL_ARC_TYPE_NAME, record);
        }
        return insert;
    }

    @Override
    public int insertSelective(ArcType record) {
        return arcTypeMapper.insertSelective(record);
    }

    @Override
    public ArcType selectByPrimaryKey(Integer arcTypeId) {
        return arcTypeMapper.selectByPrimaryKey(arcTypeId);
    }

    @Override
    public int updateByPrimaryKeySelective(ArcType record) {
        return arcTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ArcType record) {
        return arcTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<ArcType> findAll(String name, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<ArcType> list = arcTypeMapper.findAll(name);
        PageInfo<ArcType> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<ArcType> selectAll() {
        return arcTypeMapper.selectAll();
    }


}
