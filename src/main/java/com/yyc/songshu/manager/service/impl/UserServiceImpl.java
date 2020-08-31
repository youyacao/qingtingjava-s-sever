package com.yyc.songshu.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yyc.songshu.manager.dao.SmsDAO;
import com.yyc.songshu.manager.dao.UsersDAO;
import com.yyc.songshu.manager.pojo.Sms;
import com.yyc.songshu.manager.pojo.Users;
import com.yyc.songshu.manager.service.UserService;
import com.yyc.songshu.manager.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersDAO usersDAO;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private SmsDAO smsDAO;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String userLogin(String userData) {
        try {
            System.out.println(userData);
            String account = JsonUtil.dataValue(userData, "account");
            String code = JsonUtil.dataValue(userData, "code");
            String authCode = RedisUtil.stringGet("code::" + account);
            assert code != null;
            System.out.println(account+":"+code);
            if (code.equals(authCode)) {
                String token = TokenUtil.getToken(account + authCode);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                Users users = new Users();
                users.setPhone(account);
                users.setApiToken(token);
                users.setUsername(account);
                users.setNickname(account);
                users.setEmail(account);
                users.setUpdatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
                users.setCreatedAt(Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)));
                Integer userExistInt = usersDAO.selectByUserExist(account);
                if (userExistInt != null && userExistInt == 1) {
                   int updateInt = usersDAO.updateUserTokenByPhone(account,token);
                   if (updateInt>0){
                       return JsonUtil.jsonRe(jsonObject, JsonResultUtil.ok("200", "成功"));
                   }
                }else {
                    int saveInt = usersDAO.insertSelective(users);
                    if (saveInt > 0) {
                        return JsonUtil.jsonRe(jsonObject, JsonResultUtil.ok("200", "成功"));
                    }
                }
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "数据错误"));
        }catch (Exception e){
            logger.error(e+":登录");
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "手机号/验证码错误"));
        }
    }

    @Override
    public String sendCodeByPhone(String phoneNumber) {
        try {
            String phone = JsonUtil.dataValue(phoneNumber,"phone");
            System.out.println(phone);
            //Sms sms = smsDAO.selectByType("register");
            CommonRequest request = new CommonRequest();
            //request.putQueryParameter("SignName", sms.getSname());
            request.putQueryParameter("SignName", "喜桃视频");
            request.putQueryParameter("TemplateCode", "SMS_196230305");
            String sendState = AliUtil.sendMSM(phone, request, "LTAI4GCDkYTqRS88yNXDicdQ", "mSQ5RYbfGW5ElIFhR21j7zJCPQra1W");
            if (sendState.equals("OK")) {
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("200", "成功"));
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", sendState));
        }catch (Exception e){
            logger.error(e+":获取验证码");
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "号码错误"));
        }
    }

    @Override
    public String getUsersInfo() {
        try {
            String token = request.getHeader("token");
            Users users = usersDAO.selectUserInfoByToken(token);
            System.out.println(users);
            return JsonUtil.jsonRe(users, JsonResultUtil.ok("200", "成功"));
        }catch (Exception e){
            logger.error(e+":获取用户信息");
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请求错误"));
        }

    }

    @Override
    public String updateInfo(MultipartFile file, String userData) {
        try {
            String fileType = FileUtil.formUpdateFile(file);
            Gson g = new Gson();
            Users users = g.fromJson(userData, Users.class);
            String token = request.getHeader("token");
            users.setApiToken(token);
            if (!fileType.equals("Failure")){
                users.setAvatar(fileType);
            }
            int updateInfo = usersDAO.updateByTokenSelective(users);
            if (updateInfo > 0) {
                return JsonUtil.jsonRe(null, JsonResultUtil.ok("200", "成功"));
            }
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "修改失败"));
        }catch (Exception e){
            logger.error(e+":修改用户信息");
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "请求错误"));
        }
    }

    @Override
    public String getAllUser(int limit,int page) {
        PageHelper.startPage(limit,page);
        List<Users> users = usersDAO.selectAllUser();
        PageInfo<Users> usersPageInfo = new PageInfo<>(users);
        return JsonUtil.jsonRe(usersPageInfo, JsonResultUtil.ok("200", "成功"));
    }

    @Override
    public String updateUser(Users users) {
        int updateInt  = usersDAO.updateByPrimaryKeySelective(users);
        if (updateInt>0){
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("200", "成功"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "数据错误"));
    }

    @Override
    public String deleteUser(int uId) {
        int deleteInt = usersDAO.deleteByPrimaryKey(uId);
        if (deleteInt>0){
            return JsonUtil.jsonRe(null, JsonResultUtil.ok("200", "成功"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.ok("400", "数据错误"));
    }

    @Override
    public String getAdminUserInfo(int uId) {
        List<Users> users = new ArrayList<>();
        users.add(usersDAO.selectByPrimaryKey(uId));
        return JsonUtil.jsonRe(users, JsonResultUtil.ok("200", "成功"));
    }
    //基本配置（-短信配置-储存配置-关于我们），视频管理（分类管理，视频列表），用户列表，频道管理，侵权申述，评论管理

}
