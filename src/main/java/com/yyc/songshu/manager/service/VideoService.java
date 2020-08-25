package com.yyc.songshu.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的作品
     */
    String selectMyProduction(String data);

    /**
     * @param page 页码
     * @param limit 页数
     * @return 获取最新视频
     */
    String getNewsetVideo(String data);

    /**
     * @param searchData 搜索数据
     * @return 搜索结果
     */
    String searchVideo(String searchData);

    /**
     * @param videoData video数据
     * @return 成功与否
     */
    String addVideo(MultipartFile multipartFile, String videoData);

    /**
     * @param videoData video数据
     * @return 详细数据
     */
    String getVideoInfo(String videoData);

    String getVideoInfoRandom();

    String getVideoByCategoryId(String data);

    String getVideoAll(int limit,int page);
}
