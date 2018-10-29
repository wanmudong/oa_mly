package com.oa.me.modules.news.service;



import com.baomidou.mybatisplus.service.IService;
import com.oa.me.modules.common.utils.PageInfo;
import com.oa.me.modules.news.entity.Article;

import java.util.Map;

/**
 * 文章管理
 *
 * @author chenliangliang
 * @date 2018-02-21 15:42:00
 */
public interface ArticleService extends IService<Article> {



    PageInfo queryPage(Map<String, Object> params);

    Article getDetail(Long id);


    PageInfo<Map<String,Object>> getIdAndTitle(Integer pageNum, Integer pageSize, Integer catalogId);

    void auditArticle(Long id, String commment);

    Integer updateAudit(Long id, Long state, String comment);

    Boolean insertArticle(String json);

    String uploadArticle();
}

