package com.zjgsu.dao;

import com.zjgsu.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Spect on 2018/10/8.
 * user_info
 * 数据库的增删查改等操作在这里执行
 * MyBatis MapperScan 会扫描到这里，完成sql语句与相关操作语句的映射
 */
@Repository
public interface UserDao {

    @Select( "SELECT * FROM user_info WHERE username=#{username}" )
    User findByUsername( String username );

    @Select( "SELECT * FROM user_info" )
    List< User > findAll( );

    @Select( "SELECT email FROM user_info WHERE username=#{username}" )
    String getEmailByUsername( String username );

    @Insert( "INSERT INTO user_info(username, password, phone, email) VALUES(#{username}, #{password}, #{phone}, #{email})" )
    int insertUser( User user );

    @Delete( "DELETE FROM user_info WHERE id=#{id}" )
    int deleteUser( User user );

    @Update( "UPDATE user_info SET username=#{username}, password=#{password}, phone=#{phone}, email=#{email} WHERE id=#{id}" )
    int updateUser( User user );

    @Update( "UPDATE user_info SET password=#{password} WHERE username=#{username}" )
    int updatePasswordByUsername( User user );

}

