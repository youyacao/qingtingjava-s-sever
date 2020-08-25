package com.yyc.songshu.manager;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yyc.songshu.manager.util.AliUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.ServerException;
import java.util.Random;

@SpringBootTest
class ManagerApplicationTests {

    @Test
    void contextLoads() {
        //AliUtil.sendMSM("18215634373");
       /* DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIyjKGoQlvJ7rr", "n8uJvRZb7ysOsn3irr6baWxJoRdrcr");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18215634373");
        request.putQueryParameter("SignName", "EvanStudio");
        request.putQueryParameter("TemplateCode", "SMS_165117248");
        request.putQueryParameter("TemplateParam", "{\"code\":\"1234\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }*/
    }

}
