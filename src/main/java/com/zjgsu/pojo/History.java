package com.zjgsu.pojo;

/**
 * Created by Spect on 2018/11/25.
 */
public class History {
    private int id;
    private String username;
    private String monitor_date;
    private String website;
    private String msg_push_method;
    private String keyword;

    public History( ) {
    }

    public History( int id, String username, String monitor_date, String website, String msg_push_method, String keyword ) {
        this.id = id;
        this.username = username;
        this.monitor_date = monitor_date;
        this.website = website;
        this.msg_push_method = msg_push_method;
        this.keyword = keyword;
    }

    public int getId( ) {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getUsername( ) {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getMonitor_date( ) {
        return monitor_date;
    }

    public void setMonitor_date( String monitor_date ) {
        this.monitor_date = monitor_date;
    }

    public String getWebsite( ) {
        return website;
    }

    public void setWebsite( String website ) {
        this.website = website;
    }

    public String getMsg_push_method( ) {
        return msg_push_method;
    }

    public void setMsg_push_method( String msg_push_method ) {
        this.msg_push_method = msg_push_method;
    }

    public String getKeyword( ) {
        return keyword;
    }

    public void setKeyword( String keyword ) {
        this.keyword = keyword;
    }

    @Override
    public String toString( ) {
        return "History{" + "id=" + id + ", username='" + username + '\'' + ", monitor_date='" + monitor_date + '\'' + ", website='" + website + '\'' + ", msg_push_method='" + msg_push_method + '\'' + ", keyword='" + keyword + '\'' + '}';
    }
}
