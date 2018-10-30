package com.oa.me.domain;


import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/18
 */
@Data
public class Announce {
    /**
     * 用于和数据库交互的通知的实体
     */
    private int id;
    private String title;
    private String content;
    private int  publish_time;
    private int status;
    private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Announce{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publish_time=" + publish_time +
                ", uid=" + uid +
                '}';
    }
}
