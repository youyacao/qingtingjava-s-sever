package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.MessageDAO;
import com.yyc.songshu.manager.pojo.Message;
import com.yyc.songshu.manager.service.MessageService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;
    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);
    @Override
    public String getMessageList(String messageData) {
        try {
            String page = JsonUtil.dataValue(messageData, "page");
            String limit = JsonUtil.dataValue(messageData, "limit");
            String type = JsonUtil.dataValue(messageData, "type");
            assert page != null;
            assert limit != null;
            assert type != null;
            List<Message> messages = messageDAO.selectMyMessage(Integer.valueOf(type), Integer.valueOf(limit), Integer.valueOf(page));
            return JsonUtil.jsonRe(messages, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":我的消息");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "数据错误"));

        }
    }
}
