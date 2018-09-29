package com.oa.me.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/28
 */
public interface FeedbackService {
    List getAllFeedback();

    boolean setFeedback(int type, String content, int uid, HttpServletRequest request);
}
