package com.yyc.songshu.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yyc.songshu.manager.dao.*;
import com.yyc.songshu.manager.pojo.Article;
import com.yyc.songshu.manager.pojo.Video;
import com.yyc.songshu.manager.service.VideoService;
import com.yyc.songshu.manager.threads.VideoListThread;
import com.yyc.songshu.manager.util.DataManage;
import com.yyc.songshu.manager.util.FileUtil;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private CollectDAO collectDAO;

    private static Logger logger = Logger.getLogger(VideoServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private Auth auth;

    @Autowired
    private UploadManager uploadManager;

    private StringMap putPolicy;

    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public String selectMyProduction(String  data) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<Video> videos = videoDAO.selectMyVideo(uId,Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(videosList(videos,uId), JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
                logger.error(e+":我的作品");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String getNewsetVideo(String data) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<Video> videos = videoDAO.selectNewestVideo(Integer.valueOf(page), Integer.valueOf(limit));
            int count = videoDAO.selectCount();
            return getString(page, limit, count, videosList(videos,uId));
        }catch (Exception e){
            logger.error("最新视频:",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String searchVideo(String searchData) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            String keyWord = JsonUtil.dataValue(searchData, "keyWord");
            String page = JsonUtil.dataValue(searchData, "page");
            String limit = JsonUtil.dataValue(searchData, "limit");
            assert limit != null;
            assert page != null;
            int pageInt = Integer.parseInt(page) * Integer.valueOf(limit);
            List<Video> videos = videoDAO.selectVideoAndUsersByKeyWord(Integer.parseInt(page), Integer.valueOf(limit), keyWord);
            System.out.println("返回："+videosList(videos,uId));
            return JsonUtil.jsonRe(videosList(videos,uId), JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":查询video");
            e.printStackTrace();
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String addVideo(MultipartFile multipartFile, String videoData) {
        try {
            System.out.println(multipartFile+":"+videoData);
            if (multipartFile==null){
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "文件接收为空"));
            }
            if (videoData==null){
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "附带参数为空"));
            }
            String fileType = FileUtil.uploadQiNiu(multipartFile,auth, DataManage.getQiniuLocalPath(),DataManage.getQiniuBucket(),putPolicy,uploadManager);
            Gson g = new Gson();
            System.out.println(videoData);
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                    if (uId==null){
                        return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
                    }
                }catch (Exception ignored){
                    return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登录"));
                }
            }
            Video video = g.fromJson(videoData,Video.class);
            //Article article = g.fromJson(videoData,Article.class);
            video.setThumb(DataManage.getQiniuPath()+fileType);
            //video.setType(2);
            video.setUserId(uId);
            video.setUpdatedAt((Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10))));
            video.setCreatedAt((Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10))));
            int insertInt = videoDAO.insertSelective(video);
            if (insertInt > 0) {
                logger.info("上传视频成功:"+fileType);
                return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e+":添加视频");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "文件异常"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "文件上传失败"));
    }

    @Override
    public String getVideoInfo(String videoData) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            String vId = JsonUtil.dataValue(videoData, "vId");
            assert vId != null;
            List<Video> videos = videoDAO.selectVideoByIdOrMixIdMinId(Integer.valueOf(vId));
            return JsonUtil.jsonRe(videosList(videos,uId), JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error("视频详情:",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }
    }

    @Override
    public String getVideoInfoRandom() {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            List<Integer> integers = videoDAO.selectAllId();
            Random random = new Random();
            int n = random.nextInt(integers.size());
            List<Video> v = videoDAO.selectVideoInfoById(integers.get(n));
            return JsonUtil.jsonRe(videosList(v, uId), JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "服务器错误"));
        }
    }

    @Override
    public String getVideoByCategoryId(String data) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            String cId = JsonUtil.dataValue(data,"categoryId");
            List<Video> videos = videoDAO.selectVideoByCategoryId(Integer.valueOf(page), Integer.valueOf(limit),Integer.valueOf(cId));
            int count = videoDAO.selectCountByCid(Integer.valueOf(cId));
            return getString(page, limit, count, videosList(videos, uId));
        }catch (Exception e){
            logger.error("最新视频:",e);
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    private String getString(String page, String limit, int count, List<Video> videos2) {
        int totalPage = count/Integer.valueOf(limit);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("video", videos2);
        jsonObject.put("page",page);
        jsonObject.put("limit",limit);
        jsonObject.put("total",count);
        jsonObject.put("totalPage",totalPage);
        return JsonUtil.jsonRe(jsonObject, JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String getVideoAll(int limit, int page) {
        try {
            Integer uId = null;
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    uId = usersDAO.selectUserIdByToken(token);
                }catch (Exception ignored){

                }
            }
            PageHelper.startPage(limit, page);
            List<Video> users = videoDAO.selectAllVideo();
            PageInfo<Video> usersPageInfo = new PageInfo<>(videosList(users, uId));
            return JsonUtil.jsonRe(usersPageInfo, JsonResultUtil.ok("200", "成功"));
        }catch (Exception e){
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "服务器错误"));
        }
    }

    private synchronized List<Video> videosList (List<Video> videos,Integer id) throws Exception{
        CompletionService<List<Video>> completionService = new ExecutorCompletionService<List<Video>>(executorService);
        completionService.submit(new VideoListThread(videos,id,collectDAO,likeDAO));
        List<Video> videoList = completionService.take().get();
        System.out.println(":----:"+videoList);
        return videoList;
    }
}
