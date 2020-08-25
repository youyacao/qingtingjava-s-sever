package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserWithdrawLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserWithdrawLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWithdrawLog record);

    int insertSelective(UserWithdrawLog record);

    UserWithdrawLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWithdrawLog record);

    int updateByPrimaryKey(UserWithdrawLog record);

    List<UserWithdrawLog> selectMyWithdrawLog (@Param("page") int page, @Param("limit") int limit);
}