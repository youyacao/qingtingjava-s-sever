package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * @param messageDate 数据
     * @return 通知集合
     */
    @RequestMapping(method = RequestMethod.POST,value = "/messageList")
    public String getMessageAll(@RequestBody String messageDate)
    {
        return messageService.getMessageList(messageDate);

    }
}
