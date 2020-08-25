package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.AdvertViewDAO;
import com.yyc.songshu.manager.pojo.AdvertView;
import com.yyc.songshu.manager.service.AdvertViewService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertViewServiceImpl implements AdvertViewService {
    @Autowired
    private AdvertViewDAO advertViewDAO;

    private static Logger logger = Logger.getLogger(AdvertViewServiceImpl.class);
    @Override
    public String getMyRefer(String data) {
        try {
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<AdvertView> advertViews = advertViewDAO.selectMyRefer(Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(advertViews, JsonResultUtil.ok("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的推广");
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请求错误"));
        }
    }
}
