package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.FollowServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {
    @Autowired
    private FollowServcie followServcie;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的关注
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myFollow")
    public String selectMyFollow(@RequestBody String Data){
        return followServcie.myFollow(Data);
    }

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的粉丝
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myFans")
    public String selectMyFans(@RequestBody String data){
        return followServcie.myFans(data);
    }
}
