package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserVipLog;

public interface UserVipLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVipLog record);

    int insertSelective(UserVipLog record);

    UserVipLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVipLog record);

    int updateByPrimaryKey(UserVipLog record);
}