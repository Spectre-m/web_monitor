package com.zjgsu.service;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Spect on 2018/11/12.
 * 用户登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o ) throws Exception {
        String username = ( String ) httpServletRequest.getSession( ).getAttribute( "username" );
        String urlString = httpServletRequest.getRequestURI( );

        // 通过判断头部信息，过滤 Ajax 请求
/*        if ( httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") ) {
            return true;
        }*/

        // urlString.endsWith( "login" ) 过滤未登陆时的 Ajax 请求
        // 判断是否为登陆页面，如果是，则不拦截，以防无限重定向
        if ( urlString.endsWith( "login.html" ) || urlString.endsWith( "login" ) ) {
            return true;
        }

        // 不拦截注册页面
        if ( urlString.endsWith( "enroll.html" ) ) {
            return true;
        }

        // 不拦截找回密码页面
        if ( urlString.endsWith( "find_password.html" ) ) {
            return true;
        }

        // 如果没有登陆，获取不到 session，则返回登陆页面。
        if ( null == username ) {
            httpServletResponse.sendRedirect( "/pages/login.html" );

            return false;
        }

        return true;
    }

    @Override
    public void postHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView ) throws Exception {

    }

    @Override
    public void afterCompletion( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e ) throws Exception {

    }
}
