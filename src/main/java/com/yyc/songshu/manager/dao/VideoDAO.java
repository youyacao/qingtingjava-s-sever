package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByPrimaryKey(Integer id);

    List<Video> selectVideoInfoById(Integer id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> selectMyVideo(@Param("id")int id,@Param("page") int page, @Param("limit") int limit);

    List<Video> selectNewestVideo(@Param("page") int page, @Param("limit") int limit);

    List<Video> selectVideoAndUsersByKeyWord(@Param("page") int page, @Param("limit") int limit,@Param("title")String title);

    List<Video> selectVideoByIdOrMixIdMinId(@Param("id")int id);

    List<Integer> selectAllId();

    List<Video> selectVideoByCategoryId(@Param("page") int page, @Param("limit") int limit,@Param("CategoryId")int cid);

    int  selectCountByCid(int cid);

    int  selectCount();

    List<Video> selectAllVideo();

    int likeOffVideoById(int id);

    int likeOnVideoById(int id);

    int addVideoCommentById(int id);

    int addCollectById(int id);

    int offCollectById(int id);
}