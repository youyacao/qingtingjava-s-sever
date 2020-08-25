package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.AdvertViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertViewController {
    @Autowired
    private   AdvertViewService advertViewService;

    @RequestMapping(method = RequestMethod.POST,value = "/refer")
    public String getMyRefer(String data){
        return advertViewService.getMyRefer(data);
    }
}
