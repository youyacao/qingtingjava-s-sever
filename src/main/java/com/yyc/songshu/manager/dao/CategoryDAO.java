package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Category;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CategoryDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAllByPid();
}