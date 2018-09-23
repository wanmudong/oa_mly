package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class Step {
    private int key;
    private String title;
    private String desc;

    public Step(int key, String title, String desc) {
        this.key = key;
        this.title = title;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
