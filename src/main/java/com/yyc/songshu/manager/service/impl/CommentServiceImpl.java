package com.yyc.songshu.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yyc.songshu.manager.dao.CommentDAO;
import com.yyc.songshu.manager.dao.CommentLikeDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.dao.VideoDAO;
import com.yyc.songshu.manager.pojo.Comment;
import com.yyc.songshu.manager.pojo.CommentLike;
import com.yyc.songshu.manager.service.CommentService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private CommentLikeDAO commentLikeDAO;

    private static Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Override
    public String addComment(String commentData) {
        try {
            Gson gson = new Gson();
            Integer uId = null;
            String token =  request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            System.out.println(commentData);
            Comment comment = gson.fromJson(commentData, Comment.class);
            System.out.println(comment);
            comment.setUserId(uId);
            comment.setUpdatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            comment.setCreatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
            int saveInt = commentDAO.insertSelective(comment);
            if (saveInt > 0) {
                int updateInt = videoDAO.addVideoCommentById(Integer.valueOf(comment.getVid()));
                if (updateInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
                }
            }
        }catch (Exception e){
            logger.error("添加评论:",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
    }

    @Override
    public String selectCommentAll(String commentData) {
        try {
            String page = JsonUtil.dataValue(commentData, "page");
            String limit = JsonUtil.dataValue(commentData, "limit");
            String type = JsonUtil.dataValue(commentData, "type");
            String vid = JsonUtil.dataValue(commentData, "vid");
            assert type != null;
            assert vid != null;
            assert limit != null;
            assert page != null;
            System.out.println(page+":"+limit+":"+type+":"+vid+":"+commentData);
            int count = commentDAO.selectCommentCount(Integer.parseInt(vid));
            int totalPage = count/Integer.valueOf(limit);
            List<Comment> comments = commentDAO.selectCommentByTypeAndVid(Integer.parseInt(type), Integer.parseInt(vid), Integer.parseInt(limit), (Integer.parseInt(page)-1));
            String token =  request.getHeader("token");
            int uId = usersDAO.selectUserIdByToken(token);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",commentList(comments,commentDAO,uId));
            jsonObject.put("commentNum",count);
            jsonObject.put("page",page);
            jsonObject.put("limit",limit);
            jsonObject.put("total",count);
            jsonObject.put("totalPage",totalPage);
            return JsonUtil.jsonRe(jsonObject, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error("获取评论",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }
    }

    @Override
    public String getChildComment(String data) {
        try {
            String page = JsonUtil.dataValue(data, "page");
            String limit = JsonUtil.dataValue(data, "limit");
            String pid = JsonUtil.dataValue(data, "pid");
            int count = commentDAO.selectChildCommentCount(Integer.parseInt(pid));
            int totalPage = count / Integer.valueOf(limit);
            List<Comment> comments = commentDAO.selectChildComment(Integer.parseInt(pid), Integer.parseInt(page), (Integer.parseInt(limit)-1));
            JSONObject jsonObject = new JSONObject();
            String token =  request.getHeader("token");
            int uId = usersDAO.selectUserIdByToken(token);
            jsonObject.put("list", commentList(comments,commentDAO,uId));
            jsonObject.put("commentNum", count);
            jsonObject.put("page", page);
            jsonObject.put("limit", limit);
            jsonObject.put("total", count);
            jsonObject.put("totalPage", totalPage);
            return JsonUtil.jsonRe(jsonObject, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error("子评论：",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "参数错误"));
        }
    }

    @Override
    public String getAllComment(int limit, int page) {
        PageHelper.startPage(limit,page);
        List<Comment> comments = commentDAO.selectAllComment();
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return JsonUtil.jsonRe(pageInfo, JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String delComment(int id) {
        try {
            int delInt = commentDAO.deleteByPrimaryKey(id);
            if (delInt > 0) {
                return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }catch (Exception e){
            logger.error("删除评论",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String likeOnComment(String commentId) {
        try {
            Integer uId = null;
            String token =  request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentId(Integer.valueOf(commentId));
            commentLike.setUserId(uId);
            commentLike.setStatus(1);
            int commInt = Integer.parseInt(Objects.requireNonNull(JsonUtil.dataValue(commentId, "id")));
            int updateInt = commentDAO.likeOnCommentById(commInt);
            if (updateInt > 0) {
                int addInt = commentLikeDAO.insertSelective(commentLike);
                if (addInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "评论不存在"));
        }

    }

    @Override
    public String likeOffComment(String commentId) {
        try {
            Integer uId = null;
            String token =  request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请先登录"));
                }
            }
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentId(Integer.valueOf(commentId));
            commentLike.setUserId(uId);
            commentLike.setStatus(0);
            int commInt = Integer.parseInt(Objects.requireNonNull(JsonUtil.dataValue(commentId, "id")));
            int updateInt = commentDAO.likeOffCommentById(commInt);
            if (updateInt > 0) {
                int updateLikeInt = commentLikeDAO.updateByPrimaryKeySelective(commentLike);
                if (updateLikeInt>0) {
                    return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "评论不存在"));
        }
    }

    private List<Comment> commentList (List<Comment> comments,CommentDAO commentDAO,int uid){
        for (Comment comment:comments){
            int second = (((int) Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)) - comment.getCreatedAt())) ;
            int min = ((((int) Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)) - comment.getCreatedAt())) / 60) ;
            int hour = (min / 60) ;
            System.out.println(second+":"+min+":"+hour+":"+comment.getCreatedAt());
            Integer is = commentLikeDAO.selectLikeCommentExist(uid, comment.getId());
            if (is == null) {
                comment.setIsLike("false");
            } else {
                comment.setIsLike("true");
            }
            if (comment.getLikeNum()>10000){
                double cS = comment.getLikeNum()/10000;
                comment.setLikeNumStr(String.valueOf(cS)+"W");
            }else {
                comment.setLikeNumStr(String.valueOf(comment.getLikeNum()));
            }
            int num = commentDAO.selectChildCommentCount(comment.getId());
            comment.setCommentCount(num);
            if (comment.getUsers().getAvatar()==null){
                comment.getUsers().setAvatar("null");
            }
            if (comment.getUsers().getNickname()==null){
                comment.getUsers().setNickname("null");
            }
            if (comment.getUsers().getEmail()==null){
                comment.getUsers().setEmail("null");
            }
            if ( 0<second && second< 60){
                comment.setTime(second+"秒前");
            }else if ( 0<min && min< 60){
                comment.setTime(min+"分前");
            }else if (0 < hour && hour <= 24 ){
                comment.setTime(hour+"小时前");
            }else {
                comment.setTime(hour/24+"天前");
            }

        }
        return comments;
    }


}
