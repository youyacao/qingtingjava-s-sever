package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @RequestMapping(method = RequestMethod.POST,value = "/updateSms")
    public String updateSms(@RequestBody String smsData){
        return smsService.updateSmsConfiguration(smsData);
    }
}
