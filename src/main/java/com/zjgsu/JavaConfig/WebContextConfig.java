package com.zjgsu.JavaConfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Spect on 2018/10/8.
 */
@Configuration
@MapperScan( basePackages = "com.zjgsu" )
@ComponentScan( basePackages = "com.zjgsu" )
public class WebContextConfig {

    @Bean
    public DataSource getDataSource( ) {
        BasicDataSource dataSource = new BasicDataSource( );
        // 数据库连接配置
        dataSource.setDriverClassName( "com.mysql.jdbc.Driver" );
        dataSource.setUrl( "jdbc:mysql://localhost:3306/web_monitor?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8" ); // &useUnicode=true&characterEncoding=utf-8
        dataSource.setUsername( "user02" );
        dataSource.setPassword( "123456" );
        return dataSource;
    }

    // 事务管理
    @Bean
    public DataSourceTransactionManager transactionManager( ) {
        return new DataSourceTransactionManager( getDataSource( ) );
    }

    @Bean( name = "sqlSessionFactory" )
    public SqlSessionFactory sqlSessionFactory( ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean( );
        sqlSessionFactory.setDataSource( getDataSource( ) );
        return sqlSessionFactory.getObject( );
    }

}
