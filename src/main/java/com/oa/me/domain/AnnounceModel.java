package com.oa.me.domain;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/18
 */
@Data
public class AnnounceModel {
    /**
     * 用于和前端交互的通知实体
     */
    private  String content;
    private int id;
    private String time;
    private String title;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
