package com.zjgsu.controller;

import com.zjgsu.pojo.User;
import com.zjgsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * Created by Spect on 2018/10/9.
 * User 相关行为的控制
 * 如 登陆、注册
 */
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    @Autowired
    public UserController( UserService userService, HttpSession session ) {
        this.userService = userService;
        this.session = session;
    }

    // 注册相关
    @RequestMapping( value = "/enrollPage", method = RequestMethod.GET )
    public String enrollPage( ) {

        //        return "redirect:/pages/enroll.html";
        return "enroll";
    }

    @RequestMapping( value = "/enroll", method = RequestMethod.POST )
    @ResponseBody
    public String enroll( @RequestBody User user ) {
        boolean result = userService.insertUser( user );

        if ( result ) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST )
    @ResponseBody
    public String login( @RequestBody User user ) {
        System.out.println( user );
        if ( userService.isLoginSucceed( user ) ) {
            //            System.out.print( session.getAttribute( "username" ) );
            return "success";
        } else {
            return "fail";
        }
    }

    // 登出账号
    @RequestMapping( value = "/logout", method = RequestMethod.POST )
    @ResponseBody
    public void logout( HttpSession session ) {
        session.removeAttribute( "username" );

    }

    // 获取验证码
    @RequestMapping( value = "/getVerificationCode", method = RequestMethod.POST )
    @ResponseBody
    public String getVerificationCode( String email, String username ) {

        if ( userService.getVerificationCode( email, username ) ) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 修改密码
    @RequestMapping( value = "/changePassword", method = RequestMethod.POST )
    @ResponseBody
    public String changePassword( String username, String password, String verificationCode ) {
        // System.out.println( username + " " + password + " " + verificationCode );
        if ( false == session.getAttribute( "verificationCode" ).equals( verificationCode ) ) {
            return "fail";
        }

        // 验证码匹配，可以清除之
        session.removeAttribute( "verificationCode" );

        User user = new User( );
        user.setUsername( username );
        user.setPassword( password );

        if ( userService.updatePasswordByUsername( user ) ) {
            return "success";
        } else {
            return "fail";
        }
    }


}
