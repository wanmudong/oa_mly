package com.oa.me.controller;

import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;

import com.oa.me.Service.AnnounceService;
import com.oa.me.domain.AnnounceModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/api/announce")
public class AnnounceController {
    @Resource
    private AnnounceService announceService;

    /**
     * 用于主页面通知的显示
     */
    @GetMapping("")
    public Result<AnnounceModel> getAnnounceModel() {
        Result result = new Result();
        List<AnnounceModel> list = new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);

        try {
            list = announceService.getAnnounceModel();
        }catch (Exception e){

            result.setSuccess(false);
            mo.setText("请求失败！");

            result.setMsg(mo);
            return result;

        }
        result.setSuccess(true);
        mo.setText("请求成功！");

        result.setMsg(mo);
        result.setData(list);

        return result;


    }

    /**
     * 上传通知
     */
    @PostMapping("")
    @RequiresPermissions("announce:post")
    public Result<AnnounceModel> setAnnounceModel(AnnounceModel announceModel) {

        Result result = new Result();
        List<AnnounceModel> list = new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);


        /**
         * 判断标题是否为空
         */
        if (announceModel.getTitle()==null||announceModel.getTitle().equals("")) {
            result.setSuccess(false);
            mo.setText("标题不能为空");

            result.setMsg(mo);
            result.setData(list);
            return result;
        }

        boolean issussess = announceService.setAnnounceModel(announceModel);

        result.setSuccess(issussess);
        if (issussess) {
            mo.setText("发布成功");

        } else {
            mo.setText("发布失败");
        }
        result.setMsg(mo);
        result.setData(list);
        return result;
    }

    /**
     * 用于删除一条通知，需要知道该条通知的id
     * @param id
     * @return
     */
    @GetMapping("/del")
   @RequiresPermissions("announce:delete")
    public Result<AnnounceModel> setAnnounceModel(int id) {

        Result result = new Result();
        List<AnnounceModel> list = new ArrayList<AnnounceModel>();
        Message_oa mo = new Message_oa();

        mo.setLogin(true);



        boolean issuccess = announceService.delAnnounce(id);

        result.setSuccess(issuccess);
        if (issuccess) {
            mo.setText("删除成功");
        } else {
            mo.setText("删除失败");
        }

        result.setMsg(mo);
        result.setData(list);
        return result;
    }

}
