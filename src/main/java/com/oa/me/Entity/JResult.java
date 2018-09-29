package com.oa.me.Entity;

/**
 * Created by chenjiehao on 2018/9/24
 */
public class JResult<T> {
    private boolean success;
    private Message_oa msg;
    private JData<T> data;

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

    public JData<T> getData() {
        return data;
    }

    public void setData(JData<T> data) {
        this.data = data;
    }
}
