package com.oa.me.modules.news.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.me.modules.news.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/28
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag>{


        Integer insertArticleTag(ArticleTag articleTag);

        List<Integer> queryArticleTags(@Param("article_id") Integer article_id);
}
