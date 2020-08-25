package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Config;

public interface ConfigDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}