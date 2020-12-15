package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectCommentByTypeAndVid(@Param("type")int type,@Param("vid")int vid,@Param("limit")int limit,@Param("page")int page);

    int selectCommentCount(int vid);

    List<Comment> selectChildComment(@Param("pid")int pid);

    List<Comment>  selectChild (int id);

    Integer  selectIsRe (int id);

    int selectChildCommentCount(int pid);

    List<Comment> selectAllComment();

    int likeOnCommentById(int id);

    int likeOffCommentById(int id);
}