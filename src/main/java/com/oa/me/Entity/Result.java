package com.oa.me.Entity;


import lombok.Data;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Data
public class Result<T> {
    /**
     *  返回给前端界面的实体
     */
    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 请求的信息
     */
    private Message_oa msg;

    /**
     * 请求的信息
     */
    private List<T>   data;

    /**
     * 请求的条件
     */
    private JCondition conditions;




}
