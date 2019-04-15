package com.zjgsu.dao;

import com.zjgsu.pojo.Website;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spect on 2018/10/15.
 * website_info
 * 数据库的增删查改等操作在这里执行
 * MyBatis MapperScan 会扫描到这里，完成sql语句与相关操作语句的映射
 */
@Repository
public interface WebsiteDao {

    @Select( "SELECT * FROM website_info" )
    List< Website > findAll( );

    @Select( "SELECT * FROM website_info WHERE username=#{username}" )
    List< Website > findWebsiteByUsername( String username );

    @Select( "SELECT * FROM website_info WHERE username=#{username} LIMIT #{offset}, #{rows}" )
    List< Website > findWebsiteByUsernamePaging( @Param( "username" ) String username, @Param( "offset" ) int offset, @Param( "rows" ) int rows );

    @Select( "SELECT COUNT(*) FROM website_info WHERE username=#{username}" )
    int getSumOfWebsiteByUsername( String username );

    @Insert( "INSERT INTO website_info(username, website, msg_push_method, monitor_date, web_status, new_msg, keyword, selectors) VALUES(#{username}, #{website}, #{msg_push_method}, #{monitor_date}, #{web_status}, #{new_msg}, #{keyword}, #{selectors})" )
    int insertWebsite( Website website );

    @Delete( "DELETE FROM website_info WHERE id=#{id}" )
    int deleteWebsiteById( int id );

    @Update( "UPDATE website_info SET msg_push_method=#{msg_push_method}, monitor_date=#{monitor_date}, keyword=#{keyword} WHERE id=#{id} " )
    int updateWebsite( Website website );

    @Update( "UPDATE website_info SET web_status=#{web_status} WHERE id=#{id}" )
    int updateWebStatus( Website website );

    @Update( "UPDATE website_info SET new_msg=#{new_msg} WHERE id=#{id}" )
    int updateNewMsg( Website website );
}
