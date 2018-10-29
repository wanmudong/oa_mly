package com.oa.me.modules.common.utils;

import lombok.Data;

import java.util.HashMap;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Data
public class Message_oa extends HashMap<String, Object> {
    /**
     * 请求的消息的实体
     */

    /**
     * 用户是否登录
     */
    private boolean isLogin;

    /**
     * 请求的信息的解释
     */
    private String text;

    public Message_oa(boolean isLogin, String text) {
        this.put("isLogin", isLogin);
        this.put("text", text);
    }

    public static Message_oa isNotLogin(String text){
        return new Message_oa(false,text);
    }
    public static Message_oa isLogin(String text){
        return new Message_oa(true,text);
    }
}
