package com.oa.me.modules.news.service;

import com.baomidou.mybatisplus.service.IService;
import com.oa.me.modules.news.entity.ArticleTag;

/**
 * Created by chenjiehao on 2018/10/28
 */
public interface ArticleTagService extends IService<ArticleTag> {

    Integer insertArticleTag(ArticleTag articleTag);
}
