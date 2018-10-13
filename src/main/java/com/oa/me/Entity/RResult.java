package com.oa.me.Entity;


import com.oa.me.domain.Recruit;
import lombok.Data;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Data
public class RResult<T> {
    /**
     * 招新的相关返回给前端的实体
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
    private Object data;


}
