package com.yyc.songshu.manager.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yyc.songshu.manager.datas.FianlDataManager;

import java.util.Random;

public class AliUtil {

    private static IAcsClient getClient(String accessKeyId,String secret){
        DefaultProfile profile = DefaultProfile.getProfile(FianlDataManager.getRegionId(),
               accessKeyId
                , secret);
        return new DefaultAcsClient(profile);
    }

    public static String sendMSM(String phoneNumber,CommonRequest request,String accessKeyId,String secret){
        String code = String.valueOf(new Random().nextInt(9999));
        IAcsClient clients  = getClient(accessKeyId,secret);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        //request.putQueryParameter("SignName", "EvanStudio");
        //request.putQueryParameter("TemplateCode", "SMS_165117248");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = clients.getCommonResponse(request);
            System.out.println(response.getData());
            RedisUtil.stringSet("code::"+phoneNumber,code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "Success";
    }


}
