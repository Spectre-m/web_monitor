package com.zjgsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Spect on 2018/9/29.
 */

@Controller
public class MainController {

    // 初始页面：登陆页面
    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index( ) {
        return "redirect:/pages/login.html";
    }

}
