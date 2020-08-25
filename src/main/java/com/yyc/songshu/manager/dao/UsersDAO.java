package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsersDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    int selectUserIdByToken(String token);

    Users selectUserInfoByToken(String apiToken);

    int updateByTokenSelective(Users users);

    Integer selectByUserExist(String phone);

    int updateUserTokenByPhone(@Param("phone") String phone,@Param("apiToken")String apiToken);

    List<Users> selectAllUser();
}