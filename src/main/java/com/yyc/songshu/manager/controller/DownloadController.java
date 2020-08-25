package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的下载
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myDownload")
    public String selectMydown(@RequestBody String data){
        return downloadService.myDownload(data);
    }
}
