package com.oa.me.Filter;

/**
 * Created by chenjiehao on 2018/10/13
 */

import com.oa.me.Entity.SysUser;
import org.apache.shiro.SecurityUtils;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户是否登录
 */
public class AuthFilter implements Filter {

    private Integer count = 0;

    @Override
    public void destroy() {
        // 顾名思义，在销毁时使用
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("AuthFilter运行");
        boolean isLogin = isLoginOut((HttpServletRequest) request);
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        if (isLogin) {

            chain.doFilter(request, response);
        } else {
//            不存在则跳转到返回已注销的消息
            request1.getRequestDispatcher(request1.getContextPath() + "/loginOut/msg").forward(request1, response1);

        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // 初始化操作
    }

    /**
     * 判断是否已注销
     *
     * @param request
     * @return
     */
    private boolean isLoginOut(HttpServletRequest request) {
        /**
         * 判断用户是否注销
         */
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysuser == null) {
            //用户已注销
            request.setAttribute("loginOut", true);
            request.setAttribute("loginOutMsg", "用户已注销");
        } else {
            request.setAttribute("loginOut", false);
            request.setAttribute("loginOutMsg", "");
        }
        return sysuser == null ? false : true;
    }

}
