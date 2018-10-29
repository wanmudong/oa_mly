package com.oa.me.modules.news.service.impl;

import com.baomidou.mybatisplus.plugins.Page;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oa.me.modules.common.utils.JsonMapper;
import com.oa.me.modules.common.utils.PageInfo;
import com.oa.me.modules.common.utils.PageQuery;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.entity.ArticleTag;
import com.oa.me.modules.news.entity.ResultTags;
import com.oa.me.modules.news.mapper.ArticleMapper;
import com.oa.me.modules.news.mapper.ArticleTagMapper;
import com.oa.me.modules.news.service.ArticleService;
import com.oa.me.modules.news.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
//        String title = (String)params.get("title");
//        if (StringUtils.isBlank(title)){
//            title=null;
//        }else{
//            title="%"+title;
//        }


        Page<Article> page=new PageQuery<Article>(params).getPageParam();
//        Map<String,Object> map=new HashMap<>();
//        map.put("page",page);
//        map.put("title",title);
        page.setOrderByField(null);
        page.setRecords(this.baseMapper.queryList(page));
        return new PageInfo<>(page);
    }

    @Override
    public Article getDetail(Long id) {
        return baseMapper.queryDetail(id);
    }


    @Override
    public PageInfo<Map<String, Object>> getIdAndTitle(Integer pageNum, Integer pageSize, Integer catalogId) {
        Page<Map<String, Object>> page=new Page<>(pageNum,pageSize);
        page.setRecords(baseMapper.queryIdAndTitle(page,catalogId));
        return new PageInfo<>(page);
    }

    @Override
    public void auditArticle(Long id, String commment) {

    }

    @Override
    public Integer updateAudit(Long id, Long state, String comment) {
        Integer success =  baseMapper.updateAudit(id, state, comment);
        return success;
    }

    @Override
    public Boolean insertArticle(String json) {

        Article article = JsonMapper.fromJson(json,Article.class);

        Integer success = baseMapper.insertArticle(article);

        Article article1 = baseMapper.queryArticleByTitle(article.getTitle());

        //添加到article_tags
        Integer[] tagList = article.getTagIdList();
        System.out.println(tagList);
        for (int i=0;i<=tagList.length-1;i++){
            ArticleTag at = new ArticleTag();
            at.setArticleId(article1.getId());
            at.setTagId(tagList[i]);
            articleTagService.insertArticleTag(at);
        }
        return success==1?true:false;
    }
    @Override
    public String uploadArticle(){

        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        requestHeaders.add("token", "eyJpZCI6IjIiLCJ1c2VyIjoiYWRtaW4ifQ.yYYidvSXR9l893_R-49peY0Lg9vJEz27aue-zX_pYCc");
        RestTemplate restTemplate = new RestTemplate();

        //发表文章json格式
        String url6 = "http://www.chenliangliang.xin/mly/open/api/news";

        List<Article> list = baseMapper.queryArticleUpload();
        if (list==null){
            return "success";
        }
        List<String> list_json =new ArrayList<>();

        for (Article article:list) {
            List<Integer> list_tag = articleTagMapper.queryArticleTags(article.getId());
            article.setTagIdList(list_tag.toArray(new Integer[list_tag.size()]));

            Long id = article.getId();

            article.setId(null);
            String json = JsonMapper.toJson(article);
            list_json.add(json);


            HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);
            ResponseEntity<String> response = restTemplate.exchange(url6, HttpMethod.POST, requestEntity, String.class);
            String sttr = response.getBody();

            ResultTags rs = JsonMapper.fromJson(sttr, ResultTags.class);

            if (rs.getCode() != 200) {
                return rs.getMsg();
            }
            System.out.println(sttr);
            baseMapper.updateUpload(id);

        }
        return "success";
    }

}
