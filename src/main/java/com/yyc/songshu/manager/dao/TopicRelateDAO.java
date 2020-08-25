package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.TopicRelate;

public interface TopicRelateDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicRelate record);

    int insertSelective(TopicRelate record);

    TopicRelate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicRelate record);

    int updateByPrimaryKey(TopicRelate record);
}