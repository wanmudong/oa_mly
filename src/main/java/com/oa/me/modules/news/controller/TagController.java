package com.oa.me.modules.news.controller;


import com.oa.me.modules.common.utils.Result;
import com.oa.me.modules.news.entity.Tag;
import com.oa.me.modules.news.service.ArticleService;
import com.oa.me.modules.news.service.TagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/27
 */
@RestController
@EnableAutoConfiguration
public class TagController {

    private TagService tagService;

    @Autowired
    protected TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 更新标签库
     */
    @GetMapping("api/tag/update")
    @Scheduled(cron = "0 0 21 ? * SUN")
    @RequiresPermissions("news:tag:update")
    public Result updateTag(){
            tagService.updateTag();
        return Result.OK();
    }

    /**
     * 获取标签库
     */
    @GetMapping("api/tag/get")
    @RequiresPermissions("news:tag:list")
    public Result getTag(){
        List<Tag> list = tagService.getTags();
        return Result.OK().put("data",list);
    }
    @GetMapping("in")
    public Integer ins(Integer n) {
        int result = in(n);

        System.out.println(result);

        return result;
    }
    public int in(int n ){

        if (n==1){
            return 1;
        }else {
            return n*in(n-1);
        }
    }
}
