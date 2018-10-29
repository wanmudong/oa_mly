package com.oa.me.modules.news.mapper;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.github.pagehelper.Page;
import com.oa.me.modules.news.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 * 
 * @author chenliangliang
 * @date 2018-02-21 15:42:00
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 列表页查询的信息
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param title
     */
    Page<Article> queryList();

    Page<Article>queryListByTags(@Param("tags") String tags);

    Article queryDetail(@Param("id") Long id);

    Article queryArticleByTitle(@Param("title") String title);

    List<Map<String,Object>> queryIdAndTitle(Pagination page, @Param("catalogId") Integer catalogId);

    Integer updateAudit(@Param("id") Long id,@Param("state") Long state,@Param("comment") String comment);

    Integer insertArticle(Article article);

    List<Article> queryArticleUpload();

    Integer updateUpload(@Param("id") Long id);

    Page<Article> queryArticleByUsername();




}
