package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.AdvertView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdvertViewDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertView record);

    int insertSelective(AdvertView record);

    AdvertView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertView record);

    int updateByPrimaryKey(AdvertView record);

    List<AdvertView> selectMyRefer(@Param("page") int page, @Param("limit") int limit);
}