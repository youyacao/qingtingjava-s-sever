package com.yyc.songshu.manager.service.impl;

import com.google.gson.Gson;
import com.yyc.songshu.manager.dao.SmsDAO;
import com.yyc.songshu.manager.pojo.Sms;
import com.yyc.songshu.manager.service.SmsService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsDAO smsDAO;
    @Override
    public String updateSmsConfiguration(String smsData) {
        try {
            Gson g = new Gson();
            System.out.println(smsData);
            Sms sms = g.fromJson(smsData,Sms.class);
            sms.setType("register");
            int updateInt = smsDAO.updateByType(sms);
            if (updateInt > 0){
                return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
            }
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
    }
}
