package com.zjgsu.pojo;

/**
 * Created by Spect on 2018/10/4.
 * User
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;

    public User( ) {
    }

    public User( String username, String password, String phone, String email ) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
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

    public String getPassword( ) {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getPhone( ) {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public String getEmail( ) {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Override
    public String toString( ) {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\'' + '}';
    }

}
