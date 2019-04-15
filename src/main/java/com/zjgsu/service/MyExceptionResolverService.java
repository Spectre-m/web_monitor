package com.zjgsu.service;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Spect on 2018/12/10.
 *
 * 异常处理 Service
 */
@Service
public class MyExceptionResolverService implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e ) {
        ModelAndView modelAndView = new ModelAndView( );

        modelAndView.setViewName( "redirect:/pages/error.html" );

        // 可以分类
/*        if (e instanceof MailException) {
            // todo...
        }*/
        return modelAndView;
    }
}
