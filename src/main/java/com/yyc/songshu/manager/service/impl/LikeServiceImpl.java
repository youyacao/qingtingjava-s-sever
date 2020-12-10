package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.LikeDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.dao.VideoDAO;
import com.yyc.songshu.manager.pojo.Like;
import com.yyc.songshu.manager.service.LikeService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private VideoDAO videoDAO;

    private static Logger logger = Logger.getLogger(LikeServiceImpl.class);

    @Override
    public String myLike(String data) {
        try {
            int uId = 0;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                    if (uId==0){
                        return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
                    }
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<Like> likes = likeDAO.selectMyLike(Integer.valueOf(page), Integer.valueOf(limit),uId);
            return JsonUtil.jsonRe(likes, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的喜欢");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String offLike(String data) {
        try {
            String vid = JsonUtil.dataValue(data, "vid");
            String type = JsonUtil.dataValue(data, "type");
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                    if (uId==null){
                        return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
                    }
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            int update = likeDAO.offLikeByVideo(Integer.parseInt(vid), Integer.parseInt(type), uId);
            if (update > 0) {
                int updateInt = videoDAO.likeOffVideoById(Integer.valueOf(vid));
                if (updateInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "取消成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "视频不存在"));
        }catch (Exception e){
            logger.error("取消点赞：",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String onLike(String data) {
        try {
            String vid = JsonUtil.dataValue(data, "vid");
            String type = JsonUtil.dataValue(data, "type");
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                    if (uId==null){
                        return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "用户不存在"));
                    }
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            Like like = new Like();
            like.setStatus(1);
            like.setUpdatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            like.setCreatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            like.setVid(Integer.valueOf(vid));
            like.setType(Integer.valueOf(type));
            like.setUserId(uId);
            System.out.println(like);
            int saveInt = likeDAO.insertSelective(like);
            if (saveInt > 0) {
                int updateInt = videoDAO.likeOnVideoById(Integer.valueOf(vid));
                if (updateInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "点赞成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "视频不存在"));
        }catch (Exception e){
            logger.error("点赞：",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }
}
