package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.SmsLog;

public interface SmsLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsLog record);

    int insertSelective(SmsLog record);

    SmsLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsLog record);

    int updateByPrimaryKey(SmsLog record);
}