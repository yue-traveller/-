package com.google.service;

import com.google.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{


    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsername(String userName);

    User selectUserByEmail(String email);

    User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password );

}
