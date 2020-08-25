package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.AdminLog;
import com.yyc.songshu.manager.pojo.AdminLogWithBLOBs;

public interface AdminLogDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminLogWithBLOBs record);

    int insertSelective(AdminLogWithBLOBs record);

    AdminLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdminLogWithBLOBs record);

    int updateByPrimaryKey(AdminLog record);
}