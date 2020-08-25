package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Advert;
import com.yyc.songshu.manager.pojo.AdvertKey;
import org.springframework.stereotype.Component;

@Component
public interface AdvertDAO {
    int deleteByPrimaryKey(AdvertKey key);

    int insert(Advert record);

    int insertSelective(Advert record);

    Advert selectByPrimaryKey(AdvertKey key);

    int updateByPrimaryKeySelective(Advert record);

    int updateByPrimaryKey(Advert record);
}