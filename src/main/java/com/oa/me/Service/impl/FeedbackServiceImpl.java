package com.oa.me.Service.impl;

import com.oa.me.Dao.DictDao;
import com.oa.me.Dao.FeedbackDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Service.FeedbackService;
import com.oa.me.domain.Feedback;
import com.oa.me.utils.IpUtil;
import com.oa.me.utils.timeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/28
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;
    @Resource
    private DictDao dictDao;
    @Resource
    private UserDao userDao;

    @Override
    public List getAllFeedback() {


        List<Feedback> list =  feedbackDao.getAllFeedback();
        int key = 0;
        for (Feedback feedback:list)
        {
            feedback.setKey(key);

            String depart = userDao.getDepartByUid(feedback.getUid());
            feedback.setDepart(depart);

            String name = userDao.getNameByUid(feedback.getUid());
            feedback.setName(name);

            String time = timeUtil.dateTime(feedback.getFtime(),"yyyy-MM-dd HH:mm:ss");
            feedback.setTime(time);

            String type = dictDao.getFeedbackTypeNameById(feedback.getType());
            feedback.setType(type);

            key++;
        }

        return list;
    }

    @Override
    public boolean setFeedback(int type, String content, int uid, HttpServletRequest request) {

        Feedback feedback = new Feedback();
        feedback.setType(String.valueOf(type));
        feedback.setUid(uid);
        feedback.setFtime(timeUtil.getSecondTimeNow());
        feedback.setContent(content);
        feedback.setIp(IpUtil.getIpAddr(request));


        long success = feedbackDao.setFeedback(feedback);
        if (success>0)
        {
            return true;
        }
            return false;
    }
}
