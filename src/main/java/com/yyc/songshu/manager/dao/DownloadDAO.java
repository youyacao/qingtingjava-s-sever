package com.yyc.songshu.manager.dao;

import com.yyc.songshu.manager.pojo.Download;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DownloadDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Download record);

    int insertSelective(Download record);

    Download selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);

    List<Download> selectMyDownload(@Param("page") int page, @Param("limit") int limit);
}