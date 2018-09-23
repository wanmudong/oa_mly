package com.oa.me.Entity;

/**
 * Created by chenjiehao on 2018/9/17
 */
public class Message_oa {
    private boolean isLogin;
    private String text;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
