package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserVipShop;

public interface UserVipShopDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVipShop record);

    int insertSelective(UserVipShop record);

    UserVipShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVipShop record);

    int updateByPrimaryKey(UserVipShop record);
}