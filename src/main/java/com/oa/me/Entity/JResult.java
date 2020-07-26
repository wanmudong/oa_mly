package com.oa.me.Entity;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/24
 */
@Data
public class JResult<T> {
    /**
     * 用户与招新的返回实体
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
     * 请求的数据
     */
    private JData<T> data;


}
