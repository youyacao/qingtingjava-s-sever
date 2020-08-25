package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Recharge;

public interface RechargeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
}