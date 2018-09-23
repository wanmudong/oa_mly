package com.oa.me.utils;

import org.apache.shiro.SecurityUtils;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class LoginUtil {

    public static boolean isLogin(){
        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");

        return currentUserId==null?false:true;
    }
}
