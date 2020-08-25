package com.yyc.songshu.manager.controller;


import com.yyc.songshu.manager.pojo.AppContent;
import com.yyc.songshu.manager.service.AppContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppContentController {
    @Autowired
    private AppContentService appContentService;
    @RequestMapping(method = RequestMethod.POST,value = "/getUse")
    public String getUse(){
        return appContentService.getUse();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/getPrivacy")
    public String getPrivacy(){
        return appContentService.getPrivacy();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/getTort")
    public String getTort(){
        return appContentService.getTort();
    }
    @RequestMapping(method = RequestMethod.GET,value = "/getAllContent")
    public String getAll(){
        return appContentService.getAll();
    }
    @RequestMapping(method = RequestMethod.POST,value = "/updateContent")
    public String updateContent(@ModelAttribute AppContent appContent){
        return appContentService.updateContent(appContent);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/delContent")
    public String delContent(@RequestParam(value = "aId")int aId){
        return appContentService.delContent(aId);
    }

}
