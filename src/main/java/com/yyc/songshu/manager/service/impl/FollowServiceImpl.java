package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.FollowDAO;
import com.yyc.songshu.manager.pojo.Follow;
import com.yyc.songshu.manager.service.FollowServcie;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class FollowServiceImpl implements FollowServcie {

@Autowired
private FollowDAO followDAO;
    private static Logger logger = Logger.getLogger(FollowServiceImpl.class);
    @Autowired
    private HttpServletRequest request;
    @Override
    public String myFollow(String data) {
        try {
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            String token =  request.getHeader("token");
            if (token==null||token.length()==0){
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
            }
            List<Follow> follows = followDAO.selectMyFollow(Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(follows, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的关注");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String myFans(String data) {
        try {
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            String token =  request.getHeader("token");
            if (token==null||token.length()==0){
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
            }
            List<Follow> follows = followDAO.selectMyFans(Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(follows, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的粉丝");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }
}
