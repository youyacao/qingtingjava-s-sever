package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminDao {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByAccount(String account);

    List<Admin> selectAllAdmin();

    Admin selectByToken(String account);
}