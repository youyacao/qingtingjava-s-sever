package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.View;

public interface ViewDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);
}