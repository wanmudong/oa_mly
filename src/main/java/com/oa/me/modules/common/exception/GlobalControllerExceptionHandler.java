package com.oa.me.modules.common.exception;

import com.oa.me.modules.common.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenjiehao on 2018/10/26
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "/error";

//    /**
//     * shiro抛出的UnauthorizedException 统一返回http状态码401
//     */
//    @ExceptionHandler(value = UnauthorizedException.class)
//    public ResponseEntity defaultErrorHandler() throws Exception{
//        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(value = AuthorizationException.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }


    /**
     * shiro抛出的AuthorizationException统一返回
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        return Result.error(false,"用户验证错误");
        return Result.error().isNotlogin("用户验证失败");
    }

    /**
     * shiro抛出的UnauthorizedException统一返回
     */
    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result defaultnAuthorizedExceptionErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return Result.error("用户权限不足");
    }

}
