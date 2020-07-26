//package com.oa.me.Configuration;
//
///**
// * Created by chenjiehao on 2018/10/13
// */
//
//import com.oa.me.Filter.AuthFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class CustemConfigurerAdapter {
//
//    @Bean
//    public FilterRegistrationBean authFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("authFilter");
//        AuthFilter authFilter = new AuthFilter();
//        registrationBean.setFilter(authFilter);
//        registrationBean.setOrder(1);
//        List<String> urlList = new ArrayList<String>();
//        urlList.add("/api/announce/*");
//        registrationBean.setUrlPatterns(urlList);
//        return registrationBean;
//    }
//
//}