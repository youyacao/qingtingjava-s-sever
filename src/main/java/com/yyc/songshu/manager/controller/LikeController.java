package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的喜欢
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myLike")
    public String selectMyLike(@RequestBody String data){
        return likeService.myLike(data);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/onLike")
    public String onLike(@RequestBody String data){
        return likeService.onLike(data);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/offLike")
    public String offLike(@RequestBody String data){
        return likeService.offLike(data);
    }
}
