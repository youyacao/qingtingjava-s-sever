package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Roles;

public interface RolesDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}