package com.yyc.songshu.manager.service;

public interface UserWithdrawLogService {
    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的提现记录
     */
    String selectMyWithdraw(String data);
}
