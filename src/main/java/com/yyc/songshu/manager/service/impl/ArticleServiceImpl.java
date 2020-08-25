package com.yyc.songshu.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yyc.songshu.manager.dao.ArticleDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.dto.OrderByDto;
import com.yyc.songshu.manager.pojo.Article;
import com.yyc.songshu.manager.service.ArticleService;
import com.yyc.songshu.manager.util.FileUtil;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;
    private static Logger logger = Logger.getLogger(ArticleServiceImpl.class);
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private HttpServletRequest request;
    @Override
    public String selectArticleList(String dataList) {
        try {
            int page = Integer.valueOf(Objects.requireNonNull(JsonUtil.dataValue(dataList, "page")));
            int limit = Integer.valueOf(Objects.requireNonNull(JsonUtil.dataValue(dataList, "limit")));
            int oid = Integer.valueOf(Objects.requireNonNull(JsonUtil.dataValue(dataList, "oid")));
            int cid = Integer.valueOf(Objects.requireNonNull(JsonUtil.dataValue(dataList, "cid")));
            OrderByDto orderByDto = new OrderByDto();
            orderByDto.setCid(cid);
            orderByDto.setLimit(limit);
            if (oid == 0){
                orderByDto.setOid("a.updated_at DESC");
            }else if (oid == 1){
                orderByDto.setOid("a.like_num DESC");
            }else if (oid == 2){
                orderByDto.setOid("a.share_num DESC");
            }else if (oid == 3){
                orderByDto.setOid("a.comment_num DESC");
            }
            orderByDto.setPage(page);
            List<Article> articles = articleDAO.selectArticleList(orderByDto);
            int count = articleDAO.selectCount(cid);
            int totalPage = count/limit;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total",count);
            jsonObject.put("totalPage",totalPage);
            jsonObject.put("currentPage",page);
            jsonObject.put("list",articles);
            return JsonUtil.jsonRe(jsonObject, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":图文列表");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String uploadArticle(MultipartFile file, String data) {
        try {
            String fileType = FileUtil.formUpdateFile(file);
            if (fileType.equals("Failure") || data.length() == 0) {
                return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
            }
            String token =  request.getHeader("token");
            int uId = usersDAO.selectUserIdByToken(token);
            Article article = JSONObject.parseObject(data, Article.class);
            article.setImages(fileType);
            article.setType(1);
            article.setUserId(uId);
            int insertInt = articleDAO.insertSelective(article);
            if (insertInt > 0) {
                return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }catch (Exception e){
            logger.error(e+":上传图片");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));
        }
    }
}
