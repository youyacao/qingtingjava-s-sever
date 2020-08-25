package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.UserWithdrawLogDAO;
import com.yyc.songshu.manager.pojo.UserWithdrawLog;
import com.yyc.songshu.manager.service.UserWithdrawLogService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWithdrawLogServiceImpl implements UserWithdrawLogService {
    @Autowired
    private UserWithdrawLogDAO withdrawLogDAO;
    private static Logger logger = Logger.getLogger(UserWithdrawLogServiceImpl.class);

    @Override
    public String selectMyWithdraw(String data) {
        try {
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<UserWithdrawLog> withdrawLogs = withdrawLogDAO.selectMyWithdrawLog(Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(withdrawLogs, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的提现记录");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }
}
