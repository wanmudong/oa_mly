package com.oa.me.controller;

import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.FeedbackService;
import com.oa.me.domain.User;
import com.oa.me.utils.mapperUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/28
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    /**
     * 获取所有的反馈信息
     * @return
     */
    @GetMapping("")
    public Result getAllFeedback(){

        Result result = new Result();
        List list = new ArrayList();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);
//        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (sysuser == null)
//        {
//            //用户已注销
//            result.setMsg(mo);
//            result.getMsg().setText("用户已注销");
//            return result;
//        }else {
//            mo.setLogin(true);
//        }

        boolean success = false;

      list = feedbackService.getAllFeedback();
        if (list !=null) {
            result.setData(list);

            mo.setText("获取成功");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        mo.setText("获取失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;

    }

    /**
     * 存储一个反馈信息
     * @param value
     * @param content
     * @param request
     * @return
     */
    @PostMapping("")
    public Result setFeedback(int value, String content,HttpServletRequest request){
        Result result = new Result();
        List list = new ArrayList();
        Message_oa mo = new Message_oa();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        mo.setLogin(true);
//        if (sysuser == null)
//        {
//            //用户已注销
//            result.setMsg(mo);
//            result.getMsg().setText("用户已注销");
//            return result;
//        }else {
//            mo.setLogin(true);
//        }

        int  uid = sysuser.getId();
        int  type = value;
        boolean success = feedbackService.setFeedback(type,content,uid,request);
        if (success) {

            mo.setText("反馈成功");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        mo.setText("反馈失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;
    }
}
