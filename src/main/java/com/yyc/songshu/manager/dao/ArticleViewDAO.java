package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.ArticleView;

public interface ArticleViewDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleView record);

    int insertSelective(ArticleView record);

    ArticleView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleView record);

    int updateByPrimaryKey(ArticleView record);
}