package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.SearchLog;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SearchLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchLog record);

    int insertSelective(SearchLog record);

    SearchLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchLog record);

    int updateByPrimaryKey(SearchLog record);
    List<String> selectKeyWord ();
}