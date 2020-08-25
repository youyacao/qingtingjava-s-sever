package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectMyMessage(@Param("type") int type, @Param("limit") int limit, @Param("page") int page);
}