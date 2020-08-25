package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.dto.OrderByDto;
import com.yyc.songshu.manager.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //List<Article> selectArticleList(@Param("page") int page, @Param("limit") int limit,@Param("oid")int oid,@Param("cid")int cid);
    List<Article> selectArticleList(OrderByDto orderByDto);

    int selectCount(int cid);
}