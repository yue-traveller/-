package com.google.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.google.mapper.UserMapper;
import com.google.domain.User;
import com.google.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public User selectUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectUserByUsernameAndPassword(username,password);
    }


}
