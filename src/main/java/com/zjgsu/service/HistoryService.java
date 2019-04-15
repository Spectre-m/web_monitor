package com.zjgsu.service;

import com.zjgsu.dao.HistoryDao;
import com.zjgsu.pojo.History;
import com.zjgsu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Spect on 2018/11/25.
 */
@Service
public class HistoryService {

    private final HistoryDao historyDao;

    @Autowired
    public HistoryService( HistoryDao historyDao ) {
        this.historyDao = historyDao;
    }

    // @Select( "SELECT * FROM history" )
    public List< History > findAll( ) {
        return historyDao.findAll( );
    }

    // @Select( "SELECT * FROM history WHERE username=#{username}" )
    public List< History > findHistoryByUsername( String username ) {
        return historyDao.findHistoryByUsername( username );
    }

    ///@Select("SELECT COUNT(*) FROM history WHERE username={username}")
    public int getSumOfHistoryByUsername( String username ) {
        return historyDao.getSumOfHistoryByUsername( username );
    }

    // @Select( "SELECT * FROM history WHERE username={username} LIMIT #{offset}, #{rows}" )
    public List< History > findHistoryByUsernamePaging(String username, int start, int end) {
        int offset = start - 1;
        int rows = end - start + 1;

        return historyDao.findHistoryByUsernamePaging( username, offset, rows );
    }

    // @Insert( "INSERT INTO history(username, website, msg_push_method, monitor_date, keyword) VALUES(#{username}, #{website}, #{msg_push_method}, #{monitor_date}, #{keyword}" )
    public boolean insertHistory( History history ) {
        int result = historyDao.insertHistory( history );

        return ifOpSuccess( result );
    }

    // @Delete( "DELETE FROM history WHERE id=#{id}" )
    public boolean deleteHistory( History history ) {
        int result = historyDao.deleteHistory( history );

        return ifOpSuccess( result );
    }

    // Model 转换 Website => History
    public History websiteToHistory( Website website ) {
        History history = new History( );
        history.setUsername( website.getUsername( ) );
        history.setMonitor_date( website.getMonitor_date( ) );
        history.setWebsite( website.getWebsite( ) );
        history.setKeyword( website.getKeyword( ) );
        history.setMsg_push_method( website.getMsg_push_method( ) );

        return history;
    }


    public boolean ifOpSuccess( int result ) {

        if ( 0 == result ) {
            return false;
        } else {
            return true;
        }
    }
}
