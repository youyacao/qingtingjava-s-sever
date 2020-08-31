package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.CollectDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.dao.VideoDAO;
import com.yyc.songshu.manager.pojo.Collect;
import com.yyc.songshu.manager.service.CollectService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDAO collectDAO;

    private static Logger logger = Logger.getLogger(CollectServiceImpl.class);

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String myCollect(String data) {
        try {
            String token = request.getHeader("token");
            int uId = usersDAO.selectUserIdByToken(token);
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<Collect> collects = collectDAO.selectByUserCollect(uId,Integer.valueOf(page), Integer.valueOf(limit));

        return JsonUtil.jsonRe(collectList(collects), JsonResultUtil.error("200", "成功"));
    }catch (Exception e){
            logger.error("我的收藏",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String insertCollect(String data) {
        try {
            String vid = JsonUtil.dataValue(data, "vid");
            String type = JsonUtil.dataValue(data, "type");
            if (vid==null||type==null){
                return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "参数错误"));
            }
            Integer uId = null;
            String token =  request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            Collect collect = new Collect();
            collect.setUserId(uId);
            collect.setVid(Integer.parseInt(vid));
            collect.setType(Integer.parseInt(type));
            collect.setStatus(1);
            collect.setCreatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            collect.setUpdatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            int saveInt = collectDAO.insertSelective(collect);
            if (saveInt > 0) {
                int updateInt = videoDAO.addCollectById(Integer.valueOf(vid));
                if (updateInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "收藏成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "参数错误"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String offCollect(String data) {
        try {
            String vid = JsonUtil.dataValue(data, "vid");
            String type = JsonUtil.dataValue(data, "type");
            if (vid==null||type==null){
                return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "参数错误"));
            }
            Integer uId = null;
            String token = request.getHeader("token");
            if (token != null) {
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                } catch (Exception ignored) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            Collect collect = new Collect();
            collect.setUserId(uId);
            collect.setVid(Integer.parseInt(vid));
            collect.setType(Integer.parseInt(type));
            collect.setStatus(0);
            int updateInt = collectDAO.updateByPrimaryKeySelective(collect);
            if (updateInt>0){
                int updateIsLike = videoDAO.offCollectById(Integer.valueOf(vid));
                if (updateIsLike>0){
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "取消成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "服务器错误"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    private List<Collect> collectList (List<Collect> collects){
        long newDate = System.currentTimeMillis();
        for (Collect collect:collects){
            int second = (((int) newDate - collect.getVideo().getCreatedAt())/1000) ;
            int min = ((((int) newDate - collect.getVideo().getCreatedAt())/1000) / 60) ;
            int hour = (min / 60) ;
            if (collect.getVideo().getCommentNum()>10000){
                double cS = collect.getVideo().getCommentNum()/10000;
                collect.getVideo().setCommentNumStr(String.valueOf(cS)+"W");
                System.out.println(collect.getVideo().getCommentNum()+":1:"+collect.getVideo().getCommentNumStr());
            }else {
                collect.getVideo().setCommentNumStr(String.valueOf(collect.getVideo().getCommentNum()));
            }
            if (collect.getVideo().getViewNum()>10000){
                double vS = collect.getVideo().getViewNum()/10000;
                collect.getVideo().setViewNumStr(String.valueOf(vS)+"W");
                System.out.println(collect.getVideo().getViewNum()+":2:"+collect.getVideo().getViewNumStr());
            }else {
                collect.getVideo().setViewNumStr(String.valueOf(collect.getVideo().getViewNum()));
            }
            if (collect.getVideo().getLikeNum()>10000){
                double lS = collect.getVideo().getLikeNum()/10000;
                collect.getVideo().setLikeNumStr(String.valueOf(lS)+"W");
                System.out.println(collect.getVideo().getLikeNum()+":3:"+collect.getVideo().getLikeNumStr());
            }else {
                collect.getVideo().setLikeNumStr(String.valueOf(collect.getVideo().getLikeNum()));
            }
            if ( collect.getVideo().getCollectNum()>10000){
                double lS = collect.getVideo().getLikeNum()/10000;
                collect.getVideo().setCollectNumStr(String.valueOf(lS)+"W");
                System.out.println(collect.getVideo().getLikeNum()+":3:"+collect.getVideo().getLikeNumStr());
            }else {
                collect.getVideo().setCollectNumStr(String.valueOf(collect.getVideo().getLikeNum()));
            }
            if (collect.getVideo().getShareNum()>10000){
                double SS = collect.getVideo().getShareNum()/10000;
                collect.getVideo().setShareNumStr(String.valueOf(SS)+"W");
                System.out.println(collect.getVideo().getShareNum()+":4:"+collect.getVideo().getShareNumStr());
            }else {
                collect.getVideo().setShareNumStr(String.valueOf(collect.getVideo().getShareNum()));
            }

            if ( 0<second && second< 60){
                collect.getVideo().setTime(second+"秒前");
            }else if ( 0<min && min< 60){
                collect.getVideo().setTime(min+"分前");
            }else if (0 < hour && hour <= 24 ){
                collect.getVideo().setTime(hour+"小时前");
            }else {
                collect.getVideo().setTime(hour/24+"天前");
            }
        }
        System.out.println(collects);
        return collects;
    }
}
