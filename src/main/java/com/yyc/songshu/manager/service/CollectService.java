package com.yyc.songshu.manager.service;

public interface CollectService {
    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的收藏
     */
    String myCollect(String data);

    String insertCollect(String data);

    String offCollect(String data);
}
