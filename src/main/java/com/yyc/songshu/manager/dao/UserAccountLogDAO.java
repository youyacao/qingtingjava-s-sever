package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserAccountLog;

public interface UserAccountLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAccountLog record);

    int insertSelective(UserAccountLog record);

    UserAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAccountLog record);

    int updateByPrimaryKey(UserAccountLog record);
}