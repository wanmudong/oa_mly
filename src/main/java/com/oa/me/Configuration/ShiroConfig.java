package com.oa.me.Configuration;

import com.oa.me.Filter.MyFormAuthenticationFilter;
import com.oa.me.utils.shiro.LoginRetryCredentialsMatcher;
import com.oa.me.utils.shiro.MyShiroRealm;
import com.oa.me.utils.shiro.tool.CustomCredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        /**
         * anon:所有url都都可以匿名访问
         * authc: 需要认证才能进行访问
         */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断

        //解决登录成功后不跳转的问题
        Map map= new LinkedHashMap();
        map.put("anthc",new MyFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(map);
        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/api/login", "authc");
        filterChainDefinitionMap.put("/hello", "authc");
        filterChainDefinitionMap.put("/userList", "authc");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

//        未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        System.out.println("hashedCredentialsMatcher!");
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
////        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//        return hashedCredentialsMatcher;
//    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean(name = "shiroCacheManager")
    public CacheManager cacheManager(/*EhCacheCacheManager cacheManager*/) {

        System.out.println("shiroCacheManager注入成功！");
        EhCacheManager ehCacheManager = new EhCacheManager();
        // ehCacheManager.setCacheManager(cacheManager.getCacheManager());
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        ehCacheManager.init();
        return ehCacheManager;

    }

    /**
     * 凭证匹配器
     */
    @Bean
    public LoginRetryCredentialsMatcher credentialsMatcher() {
        LoginRetryCredentialsMatcher credentialsMatcher = new LoginRetryCredentialsMatcher();
        credentialsMatcher.setCacheManager(cacheManager());
        credentialsMatcher.setLoginRetryCacheName("loginRetry");
        credentialsMatcher.setMaxRetryCount(5);
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        myShiroRealm.setCredentialsMatcher(credentialsMatcher());
        myShiroRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


//
//    @Bean
//    public DefaultWebSecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//        System.out.println("securityManager");
//        return securityManager;
//    }
//
//
//    /**
//     * 凭证匹配器
//     */
//    @Bean
//    public LoginRetryCredentialsMatcher credentialsMatcher() {
//        LoginRetryCredentialsMatcher credentialsMatcher = new LoginRetryCredentialsMatcher();
//        credentialsMatcher.setCacheManager(cacheManager());
//        credentialsMatcher.setLoginRetryCacheName("loginRetry");
//        credentialsMatcher.setMaxRetryCount(5);
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(1);
//        System.out.println("credentialsMatcher！");
//        return credentialsMatcher;
//    }
//
//    /**
//     * 缓存管理器 使用Ehcache实现
//     */
//    @Bean(name = "shiroCacheManager")
//    public CacheManager cacheManager(/*EhCacheCacheManager cacheManager*/) {
//
////        log.info("shiroCacheManager注入成功！");
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        // ehCacheManager.setCacheManager(cacheManager.getCacheManager());
//        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        ehCacheManager.init();
//        System.out.println("shiroCacheManager注入成功！");
//        return ehCacheManager;
//
//    }
//
//    /**
//     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
//                new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
}