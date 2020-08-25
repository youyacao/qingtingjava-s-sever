package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Permissions;

public interface PermissionsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}