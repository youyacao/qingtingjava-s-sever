package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.CommentLike;

public interface CommentLikeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    CommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);
}