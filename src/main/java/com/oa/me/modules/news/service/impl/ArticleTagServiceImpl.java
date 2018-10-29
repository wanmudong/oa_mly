package com.oa.me.modules.news.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oa.me.modules.news.entity.ArticleTag;
import com.oa.me.modules.news.mapper.ArticleTagMapper;
import com.oa.me.modules.news.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * Created by chenjiehao on 2018/10/28
 */
@Service("ArticleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public Integer insertArticleTag(ArticleTag articleTag) {

        Integer success = baseMapper.insert(articleTag);

        return success;
    }
}
