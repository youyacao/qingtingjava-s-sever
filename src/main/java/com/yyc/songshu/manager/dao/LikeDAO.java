package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LikeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    List<Like> selectMyLike(@Param("page") int page, @Param("limit") int limit);

    int offLikeByVideo(@Param("vid")int vid,@Param("type")int type,@Param("userId")int userId);

    Integer selectIsLike(@Param("vid")int vid,@Param("userId")int userId);
}