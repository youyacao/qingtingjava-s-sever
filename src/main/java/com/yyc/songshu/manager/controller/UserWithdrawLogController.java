package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.UserWithdrawLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserWithdrawLogController {
    @Autowired
    private UserWithdrawLogService withdrawLogService;

    /**
     * @param page 页码
     * @param limit 页数
     * @return 我的提现记录
     */
    @RequestMapping(method = RequestMethod.POST,value = "/withdrawLog")
    public String getMyWithdrawLog(@RequestBody String data){
        return withdrawLogService.selectMyWithdraw(data);
    }
}
