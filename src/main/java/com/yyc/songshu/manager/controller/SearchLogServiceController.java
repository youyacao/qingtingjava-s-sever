package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.SearchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchLogServiceController {
    @Autowired
    private SearchLogService searchLogService;

    @RequestMapping(value = "/getHostWord",method = RequestMethod.POST)
    public String getWord(){
        return searchLogService.wordHot();
    }
}
