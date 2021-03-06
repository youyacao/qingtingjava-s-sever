package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.DownloadDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.pojo.Download;
import com.yyc.songshu.manager.service.DownloadService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class DownloadServiceImpl implements DownloadService {
   @Autowired
    private DownloadDAO downloadDAO;
    private static Logger logger = Logger.getLogger(DownloadServiceImpl.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UsersDAO usersDAO;
    @Override
    public String myDownload(String data) {
        try {
            String token =  request.getHeader("token");
            int uId = usersDAO.selectUserIdByToken(token);
            if (uId==0){
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("100", "请先登入"));
            }
            String page = JsonUtil.dataValue(data,"page");
            String limit = JsonUtil.dataValue(data,"limit");
            List<Download> downloads = downloadDAO.selectMyDownload(uId,Integer.valueOf(page), Integer.valueOf(limit));
            return JsonUtil.jsonRe(downloads, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的下载");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }
}
