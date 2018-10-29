package com.oa.me.entity;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/10/26
 */
@Data
public  class DataBean {
    private int noticeId;
    private String noticeTitle;
    private Object noticeImg;
    private long noticeCreateTime;
    private long noticeUpdateTime;
    private String noticeContent;
}