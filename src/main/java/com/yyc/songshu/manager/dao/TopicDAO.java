package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Topic;

public interface TopicDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}