package com.oa.me.entity;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Data
public class Message_oa {
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


}
