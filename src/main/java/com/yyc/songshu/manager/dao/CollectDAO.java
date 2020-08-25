package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CollectDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> selectByUserCollect(@Param("userId")int userId,@Param("page") int page, @Param("limit") int limit);

    Integer selectIsCollect(@Param("vId")int vId,@Param("userId")int userId);

}