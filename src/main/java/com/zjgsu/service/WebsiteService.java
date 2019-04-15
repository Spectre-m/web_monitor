package com.zjgsu.service;

import com.zjgsu.dao.WebsiteDao;
import com.zjgsu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Spect on 2018/11/22.
 */
@Service
public class WebsiteService {

    private final HttpSession session;
    private final WebsiteDao websiteDao;

    @Autowired
    public WebsiteService( WebsiteDao websiteDao, HttpSession session ) {
        this.websiteDao = websiteDao;
        this.session = session;
    }

    // @Select( "SELECT * FROM website_info" )
    public List< Website > findAll( ) {
        return websiteDao.findAll( );
    }

    // @Select( "SELECT * FROM website_info WHERE username=#{username}" )
    public List< Website > findWebsiteByUsername( String username ) {
        return websiteDao.findWebsiteByUsername( username );
    }

    // @Select( "SELECT * FROM website_info WHERE username=#{username} LIMIT #{start - 1}, #{end - start}" )
    public List< Website > findWebsiteByUsernamePaging( String username, int start, int end ) {
        int offset = start - 1;
        int rows = end - start + 1;
        return websiteDao.findWebsiteByUsernamePaging( username, offset, rows );
    }

    // @Select( "SELECT COUNT(*) FROM website_info WHERE username=#{username}" )
    public int getSumOfWebsiteByUsername( String username ) {
        return websiteDao.getSumOfWebsiteByUsername( username );
    }

    // @Insert( "INSERT INTO website_info(username, website, msg_push_method, monitor_date, web_status, new_msg, keyword, selectors) VALUES(#{username}, #{website}, #{msg_push_method}, #{monitor_date}, #{web_status}, #{new_msg}, #{keyword}, #{selectors})" )
    public boolean insertWebsite( Website website ) {
        String start_time = website.getStart_time();
        String end_time = website.getEnd_time();
        website.setMonitor_date( start_time + " ~ " + end_time );
        website.setWeb_status( " " );
        website.setNew_msg( " " );
        String username = ( String ) session.getAttribute( "username" );
        website.setUsername( username );

        int result = websiteDao.insertWebsite( website );

        return ifOpSuccess( result );
    }

    // @Delete( "DELETE FROM website_info WHERE id=#{id}" )
    public boolean deleteWebsiteById( int id ) {
        int result = websiteDao.deleteWebsiteById( id );

        return ifOpSuccess( result );
    }

    // @Update( "UPDATE website_info SET website=#{website}, msg_push_method=#{msg_push_method}, monitor_date=#{monitor_date}, keyword=#{keyword} WHERE id=#{id} " )
    public boolean updateWebsite( Website website ) {
        int result = websiteDao.updateWebsite( website );

        return ifOpSuccess( result );
    }

    // @Update( "UPDATE website_info SET web_status=#{web_status} WHERE id=#{id}" )
    public boolean updateWebStatus( Website website ) {
        int result = websiteDao.updateWebStatus( website );

        return ifOpSuccess( result );
    }

    // @Update( "UPDATE website_info SET new_msg=#{new_msg} WHERE id=#{id}" )
    public boolean updateNewMsg( Website website ) {
        int result = websiteDao.updateNewMsg( website );

        return ifOpSuccess( result );
    }

    // 判断监控日期有效性
    public String dateDetermine( String date ) {
        // date 2018-11-12 ~ 2018-11-16
        String[] dates = date.split( " ~ " );

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );

        try {
            Date curDate = sdf.parse( sdf.format( new Date( ) ) );
            Date startDate = sdf.parse( dates[ 0 ] );
            Date endDate = sdf.parse( dates[ 1 ] );

            if ( -1 == curDate.compareTo( startDate ) ) {
                return "aheadOfDate";
            } else if ( 1 == curDate.compareTo( endDate ) ) {
                return "outOfDate";
            } else {
                return "inDate";
            }

        }
        catch ( ParseException e ) {
            e.printStackTrace( );
        }

        return "";
    }

    public boolean ifOpSuccess( int result ) {

        if ( 0 == result ) {
            return false;
        } else {
            return true;
        }
    }
}
