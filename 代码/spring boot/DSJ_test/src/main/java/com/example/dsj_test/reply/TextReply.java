package com.example.dsj_test.reply;

import com.example.dsj_test.models.*;
import com.example.wx_wcl.message.Textmessage;
import com.thoughtworks.xstream.XStream;

import java.util.Map;

public class TextReply {
    public String getReply(Map<String,String> map,String content){
        Textmessage textmessage = new Textmessage();
        textmessage.setToUsername(map.get("FromUserName"));
        textmessage.setFromUsername(map.get("ToUserName"));
        textmessage.setContent(content);
        textmessage.setMsgType("text");
        textmessage.setCreateTime(System.currentTimeMillis()/1000);
        //将java对象转换成xml字符串
        XStream xStream = new XStream();
        xStream.processAnnotations(Textmessage.class);
        String xml=xStream.toXML(textmessage);
        return xml;
    }
}
