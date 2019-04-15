package com.zjgsu.controller;

import com.zjgsu.pojo.Website;
import com.zjgsu.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Spect on 2018/10/15.
 * Website 相关行为的控制
 * 如 删除信息、修改信息
 */
@Controller
public class WebsiteController {

    private final WebsiteService websiteService;

    @Autowired
    public WebsiteController( WebsiteService websiteService ) {
        this.websiteService = websiteService;
    }

    // 获取某个用户的 website 条数
    @RequestMapping( value = "/getSumOfWebsiteByUsername", method = RequestMethod.POST )
    @ResponseBody
    public int getSumOfWebsiteByUsername( String username ) {
        return websiteService.getSumOfWebsiteByUsername( username );
    }


    // 添加 Website
    @RequestMapping( value = "/addWebsite", method = RequestMethod.POST )
    @ResponseBody
    public String addWebsite( @RequestBody Website website ) {
        boolean result = websiteService.insertWebsite( website );

        if ( true == result ) {
            return "success";
        } else {
            return "fail";
        }

    }

    // 分页显示 Website
    @RequestMapping( value = "/findWebsiteByUsernamePaging", method = RequestMethod.POST )
    @ResponseBody
    // [ start, end ]
    public List< Website > findWebsiteByUsernamePaging( String username, int start, int end ) {
        return websiteService.findWebsiteByUsernamePaging( username, start, end );
    }

    // Delete Website By Id
    @RequestMapping(value = "/deleteWebsiteById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWebsiteById( int id ) {
        if ( websiteService.deleteWebsiteById( id ) ) {
            return "success";
        } else {
            return "fail";
        }
    }

    // Update Website
    @RequestMapping( value = "/updateWebsite", method = RequestMethod.POST)
    @ResponseBody
    public String updateWebsite(@RequestBody Website website) {
        if (websiteService.updateWebsite(website)) {
            return "success";
        } else {
            return "fail";
        }
    }

    // for test
    @RequestMapping( value = "/daoTest", method = RequestMethod.GET )
    public void daoTest( ) {
        System.out.println( websiteService.findWebsiteByUsernamePaging( "test", 1, 5 ) );
    }

}
