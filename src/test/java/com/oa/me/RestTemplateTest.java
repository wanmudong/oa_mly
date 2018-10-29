package com.oa.me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oa.me.entity.JUser;
import com.oa.me.entity.Notice;
import com.oa.me.entity.Recruit;
import com.oa.me.entity.Result;
import org.apache.poi.ss.formula.functions.T;

import org.json.JSONArray;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/10/26
 */
public class RestTemplateTest {
    /**
     * 不带参的get请求
     */
    @Test
    public void restTemplateGetTest(){
        RestTemplate restTemplate = new RestTemplate();
        Result<JUser> notice = restTemplate.getForObject("http://wanmudong.top:8080/api/getUserByStuid"
                , Result.class);
        System.out.println(notice);
    }
    /**
     * 带参的get请求
     */
    @Test
    public void restTemplateGetTest1(){
        RestTemplate restTemplate = new RestTemplate();
        Result<Recruit> notice = restTemplate.getForObject("http://localhost:8080/api/recruit/{1}"
                , Result.class,5);
        System.out.println(notice);
    }


    @Test
    public void rtGetEntity(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result> entity = restTemplate.getForEntity("http://wanmudong.top:8080/api/recruit/5"
                , Result.class);

        HttpStatus statusCode = entity.getStatusCode();
        System.out.println("statusCode.is2xxSuccessful()"+statusCode.is2xxSuccessful());

        Result body = entity.getBody();
         System.out.println("entity.getBody()"+body);


        ResponseEntity.BodyBuilder status = ResponseEntity.status(statusCode);
        status.contentLength(100);
        status.body("我在这里添加一句话");
        ResponseEntity<Class<Notice>> body1 = status.body(Notice.class);
        Class<Notice> body2 = body1.getBody();
        System.out.println("body1.toString()"+body1.toString());
    }


    /**
     * post请求测试
     */
    @Test
    public void rtPostObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/test/testPost";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("uid", "844072586@qq.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
    }

    /**
     * 带tokend的get请求测试
     */
    @Test
    public void rtTokenPostObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/test/testPost";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("uid", "844072586@qq.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
    }


    /**
     * 带参token的get以及请求
     */
    @Test
    public void restTeokenTemplateGetTest1() throws HttpClientErrorException, JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        requestHeaders.add("token", "eyJpZCI6IjIiLCJ1c2VyIjoiYWRtaW4ifQ.yYYidvSXR9l893_R-49peY0Lg9vJEz27aue-zX_pYCc");
       RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title", "测试一下1");
        map.put("content", "利用Resttemplate测试一下post接口");
        map.put("summary", "利用resttemplate");
        map.put("username", "小四");

        Integer [] arr ={1,2};
        map.put("tagIdList",arr);
        map.put("publishTime",223456789);
        map.put("cover","time.jpg");
        map.put("copyForm","mly");
        map.put("author","陈杰豪");
        map.put("read",1024);
//        Result<Recruit> notice = restTemplate.getForObject("http://localhost:8080/api/recruit/{1}"
//                , Result.class,5);
        String url = "http://wanmudong.top/api/getUserByStuid";
        String url1 = "http://localhost:8080/api/getUserByStuid1";
        //获取栏目文章列表
        String url2 = "http://www.chenliangliang.xin/mly/open/api/menu?catalogId=4&page=1&size=8";
        //通过文章id获取文章
        String url3 = "http://www.chenliangliang.xin/mly/open/api/news/6";
        //获取目录列表
        String url4 = "http://www.chenliangliang.xin/mly/open/api/catalogs";
        //获取标签列表
        String url5 = "http://www.chenliangliang.xin/mly/open/api/tags";
        //发表文章json格式
        String url6 = "http://www.chenliangliang.xin/mly/open/api/news";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);

        HttpEntity<String> requestEntity = new HttpEntity<String>(json,requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url5, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();


        System.out.println(sttr);
    }



}
