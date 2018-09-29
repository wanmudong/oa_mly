package com.oa.me.controller;

import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.AnnounceService;
import com.oa.me.Service.UserService;
import com.oa.me.domain.Announce;
import com.oa.me.domain.AnnounceModel;
import com.oa.me.domain.UserOa;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/18
 */
@RestController
@EnableAutoConfiguration
public class AnnounceController {
    @Resource
    private AnnounceService announceService;

    @GetMapping("/api/announce")
    public Result<AnnounceModel> getAnnounceModel(){
        Result result = new Result();
        List<AnnounceModel> list= new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysuser == null)
        {
            //用户已注销
            result.setMsg(mo);
            result.getMsg().setText("用户已注销");
            return result;
        }else {
            mo.setLogin(true);
        }


//            Result<AnnounceModel> result = new Result<AnnounceModel>();
//            List<AnnounceModel> list= new ArrayList<AnnounceModel>();
//            Message_oa mo = new Message_oa();

            list =  announceService.getAnnounceModel();
            if (list.isEmpty()){
                result.setSuccess(false);
//                mo.setLogin(true);
                mo.setText("请求失败！");

                result.setMsg(mo);
                return result;
            }

            result.setSuccess(true);
//            mo.setLogin(true);
            mo.setText("请求成功！");

            result.setMsg(mo);
            result.setData(list);

            return result;
    }



    @PostMapping("/api/announce")
    public Result<AnnounceModel> setAnnounceModel(AnnounceModel announceModel){
//        Result<AnnounceModel> result = new Result<AnnounceModel>();
//        List<AnnounceModel> list= new ArrayList<AnnounceModel>();
//        Message_oa mo = new Message_oa();

        Result result = new Result();
        List<AnnounceModel> list= new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysuser == null)
        {
            //用户已注销
            result.setMsg(mo);
            result.getMsg().setText("用户已注销");
            return result;
        }else {
            mo.setLogin(true);
        }




        if ( announceModel.getTitle().equals("")){
            result.setSuccess(false);
//            mo.setLogin(true);
            mo.setText("标题不能为空");

            result.setMsg(mo);
            result.setData(list);
            return result;
        }

        boolean issussess = announceService.setAnnounceModel(announceModel);

        result.setSuccess(issussess);
//        mo.setLogin(true);
        if (issussess){
        mo.setText("发布成功");

        }else {
            mo.setText("发布失败");
        }

        result.setMsg(mo);
        result.setData(list);
        return result;
    }

    @GetMapping("/api/del/announce")
    public Result<AnnounceModel> setAnnounceModel(int id){

        Result result = new Result();
        List<AnnounceModel> list= new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysuser == null)
        {
            //用户已注销
            result.setMsg(mo);
            result.getMsg().setText("用户已注销");
            return result;
        }else {
            mo.setLogin(true);
        }

        boolean issuccess= announceService.delAnnounce(id);
        result.setSuccess(issuccess);
//        mo.setLogin(true);
        if (issuccess){
            mo.setText("删除成功");

        }else {
            mo.setText("删除失败");
        }

        result.setMsg(mo);
        result.setData(list);
        return result;
    }
}
