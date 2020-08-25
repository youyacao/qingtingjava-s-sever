package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserTags;

public interface UserTagsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTags record);

    int insertSelective(UserTags record);

    UserTags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTags record);

    int updateByPrimaryKey(UserTags record);
}