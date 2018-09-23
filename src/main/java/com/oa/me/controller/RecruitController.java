package com.oa.me.controller;

import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;
import com.oa.me.Service.RecruitService;
import com.oa.me.domain.Recruit;
import com.oa.me.domain.User;
import com.oa.me.utils.LoginUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
@RestController
@EnableAutoConfiguration
public class RecruitController {
    @Resource
    private RecruitService recruitService;



    @PostMapping ("api/recruit")
    public Result<Recruit> getRecruit(Recruit recruit){
        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(LoginUtil.isLogin());
        List<Recruit> list =  new ArrayList<Recruit>();
        System.out.println(recruit.getId());
        if (recruit.getId()>0){
            //单条记录查询
             recruit = recruitService.getRecruitById(recruit.getId());
             list.add(recruit);
        }else {
            list = recruitService.getRecruitByOther(recruit);
        }

        if (list!=null)
        {
            result.setData(list);

            mo.setText("获取成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        result.setData(list);
        mo.setText("获取失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;

    }
    @PostMapping ("api/recruit/{id}")
    public Result update(@PathVariable("id") String id, String status, String desc, String depart){
        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(LoginUtil.isLogin());
        List<Recruit> list =  new ArrayList<Recruit>();

        Recruit recruit = recruitService.update(id,status,desc,depart);
        if (recruit != null)
        {
            list.add(recruit);
            result.setData(list);

            mo.setText("更新成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        result.setData(list);
        mo.setText("更新失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;
    }
    @PostMapping ("api/recruit/apply")
    public Result apply(Recruit recruit){


        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(LoginUtil.isLogin());
        List list =  new ArrayList();

        boolean success = recruitService.apply(recruit);
        if (success)
        {

            result.setData(list);

            mo.setText("申请成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        result.setData(list);
        mo.setText("申请失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;

    }

}
