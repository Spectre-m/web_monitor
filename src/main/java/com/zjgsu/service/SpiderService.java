package com.zjgsu.service;

import com.zjgsu.pojo.History;
import com.zjgsu.pojo.Website;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Spect on 2018/11/18.
 * spider service
 */
@Service
public class SpiderService {

    private final UserService userService;
    private final WebsiteService websiteService;
    private final HistoryService historyService;
    private final MailService mailService;

    @Autowired
    public SpiderService( WebsiteService websiteService, HistoryService historyService, MailService mailService, UserService userService ) {
        this.websiteService = websiteService;
        this.historyService = historyService;
        this.mailService = mailService;
        this.userService = userService;
    }

    public void spiderAllWebsite( ) {
        // 获取数据库中所有的 Website 信息
        List< Website > websiteList = websiteService.findAll( );
        ArrayList< String > stringArrayList = new ArrayList<>( );
        // 判断 url 和 selectors 刚刚否已经爬取过
        String ifNeedSpider;
        String urlAndSelectors;

        for ( Website website : websiteList ) {
            urlAndSelectors = website.getWebsite( ) + website.getSelectors( );

            if ( stringArrayList.contains( urlAndSelectors ) ) {
                ifNeedSpider = "false";
            } else {
                stringArrayList.add( urlAndSelectors );
                ifNeedSpider = "true";
            }

            System.out.println( "\n" + website.getMonitor_date( ) + "....." );

            // 判断日期是否在范围之内
            String date = website.getMonitor_date( );
            String dateDetermine = websiteService.dateDetermine( date );
            if ( dateDetermine.equals( "aheadOfDate" ) ) {
                System.out.println( "aheadOfDate\n" );
                continue;
            } else if ( dateDetermine.equals( "outOfDate" ) ) {
                // 过期的话，删除后存到 History 中
                History history = historyService.websiteToHistory( website );
                historyService.insertHistory( history );
                websiteService.deleteWebsiteById( website.getId( ) );

                continue;
            }

            // 爬虫所需参数
            String url = website.getWebsite( );
            String selectors = website.getSelectors( );
            String keyword = website.getKeyword( );

            String[] args = new String[]{ url, selectors, keyword, ifNeedSpider };

            // 调用爬虫，获取结果，判断是否有消息更新
            ArrayList< String > resultList = differentText( args );

            System.out.println( url + selectors );
            System.out.println( website.getKeyword( ) );
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
                String email = userService.getEmailByUsername( website.getUsername() );
                mailService.sendMail( email, resultList );
            }


        }

    }

    public ArrayList< String > differentText( String[] args ) {

        Properties props = new Properties( );
        props.put( "python.home", "path to the Lib folder" );
        props.put( "python.console.encoding", "UTF-8" );
        props.put( "python.security.respectJavaAccessibility", "false" );
        props.put( "python.import.site", "false" );

        Properties preprops = System.getProperties( );
        PythonInterpreter.initialize( preprops, props, new String[ 0 ] );

        ArrayList< String > stringArrayList = new ArrayList<>( );

        Process proc;
        try {
            System.out.println( "start spider......" );

            // 参数获取与传递
            String url = args[ 0 ];
            String selectors = args[ 1 ];
            String keyword = args[ 2 ];
            // 指明当前的 url + selectors 是否不久前刚刚爬取过
            String ifNeedSpider = args[ 3 ];
            String[] arg = new String[]{ "python", "src/main/python/spider04/work_space/main.py", url, selectors, keyword, ifNeedSpider };

            // 执行
            proc = Runtime.getRuntime( ).exec( arg );
            // proc = Runtime.getRuntime( ).exec( "python C:\\Users\\Spect\\IdeaProjects\\springMVC\\src\\main\\python\\test01\\work_space\\main.py" );
            //等待执行脚本的子程序执行完毕后再继续执行后面的代码
            proc.waitFor( );
            //用输入输出流来截取结果
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( proc.getInputStream( ), "GB2312" ) );

            String line;
            while ( ( line = bufferedReader.readLine( ) ) != null ) {
                stringArrayList.add( line );
            }

            bufferedReader.close( );
            proc.waitFor( );
            System.out.println( "end spider......" );
        }
        catch ( IOException | InterruptedException e ) {
            e.printStackTrace( );
        }

        return stringArrayList;

    }
}
