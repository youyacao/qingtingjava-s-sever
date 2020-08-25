package com.yyc.songshu.manager.service;

public interface DownloadService {

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的下载
     */
    String myDownload(String data);
}
