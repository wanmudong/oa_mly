package com.oa.me.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/26
 */
@Data
public class Notice {
    private int status;
    private Object msg;
    private List<DataBean> data;
}


