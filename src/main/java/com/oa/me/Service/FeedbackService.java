package com.oa.me.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/28
 */
public interface FeedbackService {
    /**
     * 获取所有的反馈信息
     * @return
     */
    List getAllFeedback();

    /**
     * 存储一条反馈信息
     * @param type
     * @param content
     * @param uid
     * @param request
     * @return
     */
    boolean setFeedback(int type, String content, int uid, HttpServletRequest request);
}
