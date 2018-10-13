package com.oa.me.domain;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/28
 */
@Data
public class Feedback {
    /**
     * 用于存储从数据库查找到的反馈信息的实体
     */
    private String content;
    private String depart;
    private int id;
    private int key;
    private int uid;
    private String name;
    private String time;
    private long ftime;
    private String type;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getFtime() {
        return ftime;
    }

    public void setFtime(long ftime) {
        this.ftime = ftime;
    }

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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
