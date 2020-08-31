package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.CommentLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CommentLikeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    CommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);

    Integer selectLikeCommentExist(@Param("userId")int uid,@Param("commentId")int cid);
}