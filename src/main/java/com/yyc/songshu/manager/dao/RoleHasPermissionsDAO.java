package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.RoleHasPermissions;

public interface RoleHasPermissionsDAO {
    int insert(RoleHasPermissions record);

    int insertSelective(RoleHasPermissions record);
}