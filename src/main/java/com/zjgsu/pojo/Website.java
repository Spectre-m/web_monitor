package com.zjgsu.pojo;

/**
 * Created by Spect on 2018/10/15.
 * Website
 */
public class Website {

    private int id;
    private String username;
    private String website;
    private String msg_push_method;
    private String monitor_date;
    private String web_status;
    private String new_msg;
    private String keyword;
    private String selectors;
    // start_time end_time 只是为了接受 json 使用，无其他用处
    private String start_time;
    private String end_time;

    public Website( ) {
    }

    public Website( int id, String username, String website, String msg_push_method, String monitor_date, String web_status, String new_msg, String keyword, String selectors, String start_time, String end_time ) {
        this.id = id;
        this.username = username;
        this.website = website;
        this.msg_push_method = msg_push_method;
        this.monitor_date = monitor_date;
        this.web_status = web_status;
        this.new_msg = new_msg;
        this.keyword = keyword;
        this.selectors = selectors;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public String getMonitor_date( ) {
        return monitor_date;
    }

    public void setMonitor_date( String monitor_date ) {
        this.monitor_date = monitor_date;
    }

    public String getWeb_status( ) {
        return web_status;
    }

    public void setWeb_status( String web_status ) {
        this.web_status = web_status;
    }

    public String getNew_msg( ) {
        return new_msg;
    }

    public void setNew_msg( String new_msg ) {
        this.new_msg = new_msg;
    }

    public String getKeyword( ) {
        return keyword;
    }

    public void setKeyword( String keyword ) {
        this.keyword = keyword;
    }

    public String getSelectors( ) {
        return selectors;
    }

    public void setSelectors( String selectors ) {
        this.selectors = selectors;
    }

    public String getStart_time( ) {
        return start_time;
    }

    public void setStart_time( String start_time ) {
        this.start_time = start_time;
    }

    public String getEnd_time( ) {
        return end_time;
    }

    public void setEnd_time( String end_time ) {
        this.end_time = end_time;
    }

    @Override
    public String toString( ) {
        return "Website{" + "id=" + id + ", username='" + username + '\'' + ", website='" + website + '\'' + ", msg_push_method='" + msg_push_method + '\'' + ", monitor_date='" + monitor_date + '\'' + ", web_status='" + web_status + '\'' + ", new_msg='" + new_msg + '\'' + ", keyword='" + keyword + '\'' + ", selectors='" + selectors + '\'' + ", start_time='" + start_time + '\'' + ", end_time='" + end_time + '\'' + '}';
    }
}
