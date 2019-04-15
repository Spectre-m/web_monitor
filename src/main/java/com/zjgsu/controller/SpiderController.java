package com.zjgsu.controller;

import com.zjgsu.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by Spect on 2018/11/18.
 * spider controller
 */
@Controller
public class SpiderController {

    private final SpiderService spiderService;

    @Autowired
    public SpiderController( SpiderService spiderService ) {
        this.spiderService = spiderService;
    }

    @RequestMapping( value = "/spider", method = RequestMethod.GET )
    public String spider( Model model ) {
        // 传递参数
        String url = "https://news.sina.com.cn/";    //url
        //        String num2 = "com.zjgsu.test";        //fold_name
        String selectors = "'#syncad_1 > h1,p:nth-of-type(4) > a,#ad_entry_b2 > ul > li.topli14,#ad_entry_b2 > ul > li'";    //selectors
        String keyword = "all";        //关键字
        String[] args = new String[]{ url, selectors, keyword };

        // 获取结果
        ArrayList< String > stringArrayList = spiderService.differentText( args );
        model.addAttribute( "result", stringArrayList );

        return "spider";
    }
}
