package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.pojo.Users;
import com.yyc.songshu.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    /**
     *
     * @return 成功与否
     */
    @RequestMapping(method = RequestMethod.POST,value = "/getCode")
    public String getPhoneNumberCode(@RequestBody String data){
        return userService.sendCodeByPhone(data);
    }

    /**
     * @param userData 用户数据
     * @return token
     */
    @RequestMapping(method = RequestMethod.POST,value = "/LRUsers")
    public String LrUsers(@RequestBody String userData){
        return userService.userLogin(userData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/userInfo")
    public String userInfo(){
        return userService.getUsersInfo();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateInfo")
    public String updateUserInfo(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "data",required = false)String userData){
        return userService.updateInfo(file,userData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUser")
    public String updateUser(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "data",required = false)String userData){
        return userService.updateInfo(file,userData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/delUser")
    public String delUserInfo(@RequestParam(value = "uId",required = false) int uId){
        return userService.deleteUser(uId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getAllUser")
    public String updateUserInfo(@RequestParam(value = "limit")int limit,@RequestParam(value = "page")int page){
        return userService.getAllUser(limit,page);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUA")
    public String updateUserInfoAdmin(@RequestBody Users users){
        System.out.println(users);
        return userService.updateUser(users);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public String login(@RequestBody String s){
        System.out.println(s);
        return "{\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"token\": \"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC94aW54aW5nLWFwaS50ZXN0LmNvbVwvYXBpXC92MVwvbG9naW4iLCJpYXQiOjE1ODU5ODU2NTUsImV4cCI6MTU4NTk4OTI1NSwibmJmIjoxNTg1OTg1NjU1LCJqdGkiOiJEM3hwcXlTYWRCU3NFSjc5Iiwic3ViIjoxMDAwMCwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.spceIQnlkXs8oal7eiph6L9yJqOeWnhppXdzt6ulDdk\",\n" +
                "        \"expires_in\": 3600\n" +
                "    }\n" +
                "}";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/user")
    public String info(@RequestParam(value = "uId")int uId){
        System.out.println();
        //System.out.println(s);
        return userService.getAdminUserInfo(uId);
    }

}