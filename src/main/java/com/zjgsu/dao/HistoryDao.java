package com.zjgsu.dao;

import com.zjgsu.pojo.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spect on 2018/11/25.
 * History Dao
 * 增删改查
 */
@Repository
public interface HistoryDao {

    @Select( "SELECT * FROM history" )
    List< History > findAll( );

    @Select( "SELECT * FROM history WHERE username=#{username}" )
    List< History > findHistoryByUsername( String username );

    @Select( "SELECT COUNT(*) FROM history WHERE username=#{username}" )
    int getSumOfHistoryByUsername( String username );

    @Select( "SELECT * FROM history WHERE username=#{username} LIMIT #{offset}, #{rows}" )
    List< History > findHistoryByUsernamePaging( @Param( "username" ) String username, @Param( "offset" ) int offset, @Param( "rows" ) int rows );

    @Insert( "INSERT INTO history(username, website, msg_push_method, monitor_date, keyword) VALUES(#{username}, #{website}, #{msg_push_method}, #{monitor_date}, #{keyword})" )
    int insertHistory( History history );

    @Delete( "DELETE FROM history WHERE id=#{id}" )
    int deleteHistory( History history );

}
