package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的收藏
     */
    @RequestMapping(method = RequestMethod.POST,value = "/myCollect")
    public String myCollect(@RequestBody String data){
        return collectService.myCollect(data);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/addCollect")
    public String addCollect(@RequestBody String data){
        return collectService.insertCollect(data);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/offCollect")
    public String offCollect(@RequestBody String data){
        return collectService.offCollect(data);
    }
}
