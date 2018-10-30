package com.oa.me.controller;

import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;
import com.oa.me.Entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjiehao on 2018/10/13
 */
@RestController
@EnableAutoConfiguration

public class LoginController {

    /**
     * 当用户注销时返回该信息
     * @return
     */
   @GetMapping("/loginOut/msg")
    public Result loginOutMsg(){
       Result result = new Result();
       Message_oa oa = new Message_oa();

       SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
       result.setSuccess(false);
       oa.setLogin(false);
       oa.setText("用户已注销");
       result.setMsg(oa);
       System.out.println(sysuser);
       return result;

    }
}
