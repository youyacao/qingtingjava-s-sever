package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Collect;
import com.yyc.songshu.manager.pojo.Follow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FollowDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<Follow> selectMyFollow(@Param("page") int page, @Param("limit") int limit);

    List<Follow> selectMyFans(@Param("page") int page, @Param("limit") int limit);

}