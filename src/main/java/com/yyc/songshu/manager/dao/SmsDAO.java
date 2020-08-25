package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Sms;
import org.springframework.stereotype.Component;

@Component
public interface SmsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Sms record);

    int insertSelective(Sms record);

    Sms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKey(Sms record);

    int updateByType(Sms sms);

    Sms selectByType(String type);
}