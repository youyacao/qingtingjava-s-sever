package com.yyc.songshu.manager.service;

public interface FollowServcie {
    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的关注
     */
    String myFollow(String data);

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的粉丝
     */
    String myFans(String data);

    String addFollow(String data);

    String offFollow(String data);
}
