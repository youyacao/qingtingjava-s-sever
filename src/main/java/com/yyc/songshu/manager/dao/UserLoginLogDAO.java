package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserLoginLog;

public interface UserLoginLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}