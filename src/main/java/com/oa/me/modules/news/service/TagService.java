package com.oa.me.modules.news.service;

import com.baomidou.mybatisplus.service.IService;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.entity.Tag;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/27
 */
public interface TagService extends IService<Tag> {

     Boolean updateTag();
     List<Tag> getTags();
}
