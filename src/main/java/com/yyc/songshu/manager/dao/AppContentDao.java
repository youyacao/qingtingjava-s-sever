package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.AppContent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppContentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AppContent record);

    int insertSelective(AppContent record);

    AppContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppContent record);

    int updateByPrimaryKeyWithBLOBs(AppContent record);

    int updateByPrimaryKey(AppContent record);

    List<AppContent> getAll();
}