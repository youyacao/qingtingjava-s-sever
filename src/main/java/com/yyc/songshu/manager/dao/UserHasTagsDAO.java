package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserHasTags;

public interface UserHasTagsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserHasTags record);

    int insertSelective(UserHasTags record);

    UserHasTags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserHasTags record);

    int updateByPrimaryKey(UserHasTags record);
}