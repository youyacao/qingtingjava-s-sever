package com.yyc.songshu.manager.service;

import com.yyc.songshu.manager.pojo.Users;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String userLogin(String userData);

    String sendCodeByPhone(String data);

    String getUsersInfo();

    String updateInfo(MultipartFile file, String userData);

    String getAllUser(int limit,int page);

    String getAllAdmin(int limit,int page);

    String updateUser(Users users);

    String deleteUser(int uId);

    String getAdminUserInfo(int uId);

    String getAdminToken(String token);

    String login(String data);

    String addAdminAccount(String data);
}
