package com.oa.me.Entity;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/24
 */
@Data
public class JCondition {
    /**
     * 查询条件
     */

    /**
     * 界别
     */
    private String period;

    /**
     * 部门
     */
    private String depart;

    /**
     * 校区
     */
    private String campus;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容
     */
    private String contact;
    private int uid;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
