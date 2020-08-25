package com.yyc.songshu.manager.service;

public interface LikeService {
    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的喜欢
     */
    String myLike(String data);

    String onLike(String data);

    String offLike(String data);
}
