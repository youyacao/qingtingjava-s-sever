package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Share;

public interface ShareDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
}