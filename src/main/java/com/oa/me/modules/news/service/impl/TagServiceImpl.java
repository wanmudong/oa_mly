package com.oa.me.modules.news.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oa.me.modules.common.utils.JsonMapper;
import com.oa.me.modules.news.entity.ResultTags;
import com.oa.me.modules.news.entity.Tag;
import com.oa.me.modules.news.mapper.TagMapper;
import com.oa.me.modules.news.service.TagService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/27
 */
@Service("TagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    public Boolean updateTag(){

        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        requestHeaders.add("token", "eyJpZCI6IjIiLCJ1c2VyIjoiYWRtaW4ifQ.yYYidvSXR9l893_R-49peY0Lg9vJEz27aue-zX_pYCc");
        RestTemplate restTemplate = new RestTemplate();

        //获取标签列表
        String url = "http://www.chenliangliang.xin/mly/open/api/tags";

        HttpEntity<String> requestEntity = new HttpEntity<String>(null,requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();

        ResultTags rs =  JsonMapper.fromJson(sttr,ResultTags.class);
        List<Tag> list = rs.getTags();
        for (Tag tag:list) {
            Tag tag1 =  baseMapper.selectById(tag.getTagId());
            if (tag1==null){
                baseMapper.insertTags(tag);
            }
        }
        return true;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = baseMapper.queryTags();
        return tags;
    }
}
