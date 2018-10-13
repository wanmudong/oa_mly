package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/18
 */
public class UserModel {
    /**
     * 用于接收登录表单的成员的实体
     */
    //用户名
    private String username;
    //密码
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
