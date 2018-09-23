package com.oa.me.Entity;


import java.util.List;

/**
 * Created by chenjiehao on 2018/9/17
 */
public class Result<T> {
    private boolean success;
    private Message_oa msg;
    private List<T>   data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Message_oa getMsg() {
        return msg;
    }

    public void setMsg(Message_oa msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
