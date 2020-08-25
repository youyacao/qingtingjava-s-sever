package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.UserHasRoles;

public interface UserHasRolesDAO {
    int insert(UserHasRoles record);

    int insertSelective(UserHasRoles record);
}