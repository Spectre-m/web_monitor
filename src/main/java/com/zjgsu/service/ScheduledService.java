package com.zjgsu.service;

import com.zjgsu.pojo.History;
import com.zjgsu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spect on 2018/11/24.
 * 定时器
 * 按一定的时间执行某个任务
 */
@Component
public class ScheduledService {

    private final SpiderService spiderService;

    @Autowired
    public ScheduledService( SpiderService spiderService ) {
        this.spiderService = spiderService;
    }

    // 爬虫定时器
   // @Scheduled( cron = "0 0/1 * * * ?" )
    public void SpiderScheduled( ) {

        spiderService.spiderAllWebsite( );
/*
        // 获取数据库中所有的 Website 信息
        List< Website > websiteList = websiteService.findAll( );
        ArrayList< String > stringArrayList = new ArrayList<>( );
        // 判断 url 和 selectors 刚刚否已经爬取过
        String ifUrlExist = null;

        for ( Website website : websiteList ) {
            ifUrlExist = "false";

            System.out.println( "\n" + website.getMonitor_date( ) + "....." );
            String date = website.getMonitor_date( );
            String dateDetermine = websiteService.dateDetermine( date );
            if ( dateDetermine.equals( "aheadOfDate" ) ) {
                System.out.println( "aheadOfDate\n" );
                continue;
            } else if ( dateDetermine.equals( "outOfDate" ) ) {
                // 过期的话，删除后存到 History 中
                History history = historyService.websiteToHistory( website );
                historyService.insertHistory( history );
                websiteService.deleteWebsite( website );

                continue;
            }

            // 爬虫所需参数
            String url = website.getWebsite( );
            String selectors = website.getSelectors( );
            String keyword = website.getKeyword( );

            String[] args = new String[]{ url, selectors, keyword, ifUrlExist };

            // 调用爬虫，获取结果，判断是否有消息更新
            ArrayList< String > resultList = spiderService.differentText( args );

            System.out.println( url + selectors );
            System.out.println( resultList );

            if ( 0 == resultList.size( ) ) {
                System.out.println( "NULL" );
                continue;
            } else if ( resultList.get( 0 ).equals( "没有更新消息" ) ) {
                System.out.println( "没有更新消息" );
                continue;
            } else if ( resultList.get( 0 ).equals( "没有相关关键字" ) ) {
                System.out.println( "没有相关关键字" );
                continue;
            }

            if ( website.getMsg_push_method( ).equals( "邮箱" ) ) {
                mailService.sendMail( website, resultList );
            }


        }
*/

    }

    /*
    * [No closing quotation, No closing quotation, 没有更新消息]
    * [https://news.sina.com.cn/c/gat/2018-11-24/doc-ihpevhck5195369.shtml, 台“九合一”民进党大败蔡英文辞去党主席, https://news.sina.com.cn/c/2018-11-24/doc-ihpevhck4884246.shtml, 十九大后老虎渐入司法程序他涉一少见罪名....]
    * [没有更新消息]
    *
    * */

}
