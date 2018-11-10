package com.oa.me.modules.news.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.me.modules.common.utils.JsonMapper;
import com.oa.me.modules.common.utils.MyPageInfo;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.entity.ArticleTag;
import com.oa.me.modules.news.entity.ResultTags;
import com.oa.me.modules.news.mapper.ArticleMapper;
import com.oa.me.modules.news.mapper.ArticleTagMapper;
import com.oa.me.modules.news.service.ArticleService;
import com.oa.me.modules.news.service.ArticleTagService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public MyPageInfo queryPage(String tags) {

        if (tags==null||"".equals(tags))
        {
            PageInfo<Article> pageInfo = new PageInfo<>(baseMapper.queryList());
            return new MyPageInfo<>(pageInfo);
        }
        String[] tagArray = tags.split(",");
        PageInfo<Article> pageInfo = new PageInfo<>(baseMapper.queryListByTags(tags));
        return new MyPageInfo<>(pageInfo);

    }

    @Override
    public Article getDetail(Long id) {
       Article article =  baseMapper.queryDetail(id);

        List<Integer> list_tag = articleTagMapper.queryArticleTags(Math.toIntExact(id));
        article.setTagIdList(list_tag.toArray(new Integer[list_tag.size()]));
        return article;
    }


   @Override
  public MyPageInfo<Map<String, Object>> getIdAndTitle(Integer pageNum, Integer pageSize, Integer catalogId) {
//        Page<Map<String, Object>> page=new Page<>(pageNum,pageSize);
//        page.setRecords(baseMapper.queryIdAndTitle(page,catalogId));
//        return new MyPageInfo<>(page);
       return  null;
    }

    @Override
    public void auditArticle(Long id, String commment) {

    }

    @Override
    public Integer updateAudit(Long id, Long pass, String comment) {
        Integer state = 1;
        if (pass==1){
            state=3;
            }else if (pass==0){
            state=1;
        }
        Integer success =  baseMapper.updateAudit(id, state, comment);
        return success;
    }

    @Override
    public Boolean insertArticle(String json) {

        Article article = JsonMapper.fromJson(json,Article.class);

        article.setPublishTime(new Date(article.getPublishTime().getTime()*1000));


        Integer success = baseMapper.insertArticle(article);

        Article article1 = baseMapper.queryArticleByTitle(article.getTitle());

        //添加到article_tags
        Integer[] tagList = article.getTagIdList();

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
        if (list==null || list.isEmpty()){ return "无文章需要更新"; }
        List<String> list_json =new ArrayList<>();

        for (Article article:list) {
            List<Integer> list_tag = articleTagMapper.queryArticleTags(Math.toIntExact(article.getId()));
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

    @Override
    public MyPageInfo<Article> getHistory(String username) {
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("username",username);
//
//        Page<Article> page=new PageQuery<Article>(params).getPageParam();
//
//
//        page.setOrderByField(null);
//        page.setRecords(this.baseMapper.queryList(page));
//
//        return new MyPageInfo<>(page);

//        List<Article> list = baseMapper.selectList(
//                new EntityWrapper<Article>().eq("username",username)
//        );
//
//
//        List<Article> userList = baseMapper.selectPage(
//                new Page<Article>(2, 3),
//                new EntityWrapper<Article>().eq("username", username)
//        );
//        System.out.println(userList);
//        page.setRecords(userList);

        PageInfo<Article> pageInfo = new PageInfo<>(baseMapper.queryArticleByUsername(username));
        return new MyPageInfo<>(pageInfo);

    }

}
