package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.VideoView;

public interface VideoViewDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoView record);

    int insertSelective(VideoView record);

    VideoView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoView record);

    int updateByPrimaryKey(VideoView record);
}