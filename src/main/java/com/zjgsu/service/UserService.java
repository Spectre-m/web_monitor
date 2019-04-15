package com.zjgsu.service;

import com.zjgsu.dao.UserDao;
import com.zjgsu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Spect on 2018/10/14.
 */
@Service
public class UserService {

    private final HttpSession session;
    private final UserDao userDao;
    private final MailService mailService;

    @Autowired
    public UserService( HttpSession session, UserDao userDao, MailService mailService ) {
        this.session = session;
        this.userDao = userDao;
        this.mailService = mailService;
    }

    // 与数据库比对，判断是否登陆成功
    public boolean isLoginSucceed( User inputUser ) {

        User storedUser = userDao.findByUsername( inputUser.getUsername( ) );

        if ( null == storedUser ) {
            return false;
        } else if ( storedUser.getPassword( ).equals( inputUser.getPassword( ) ) ) {
            session.setAttribute( "username", inputUser.getUsername( ) );

            return true;
        } else {
            return false;
        }
    }

    /**
     * user 增删改查业务
     */
    // @Select( "SELECT * FROM user_info WHERE username=#{username}" )
    public User findByUsername( String username ) {
        return userDao.findByUsername( username );
    }

    // @Select( "SELECT * FROM user_info" )
    public List< User > findAll( ) {
        return userDao.findAll( );
    }

    // @Select( "SELECT email FROM user_info WHERE username=#{username}" )
    public String getEmailByUsername( String username ) {
        return userDao.getEmailByUsername( username );
    }

    // @Insert( "INSERT INTO user_info(username, password, phone, email) VALUES(#{username}, #{password}, #{phone}, #{email})" )
    public boolean insertUser( User user ) {
        int result = userDao.insertUser( user );

        return ifOpSuccess( result );
    }

    // @Delete( "DELETE FROM user_info WHERE id=#{id}" )
    public boolean deleteUser( User user ) {
        int result = userDao.deleteUser( user );

        return ifOpSuccess( result );
    }

    // @Update( "UPDATE user_info SET username=#{username}, password=#{password}, phone=#{phone}, email=#{email} WHERE id=#{id}" )
    public boolean updateUser( User user ) {
        int result = userDao.updateUser( user );

        return ifOpSuccess( result );
    }

    // @Update( "UPDATE user_info SET password=#{password} WHERE username=#{username}" )
    public boolean updatePasswordByUsername( User user ) {
        int result = userDao.updatePasswordByUsername( user );

        return ifOpSuccess( result );
    }

    // 发送验证码，并设置有效期
    public boolean getVerificationCode( String email, String username ) {

        if ( false == ifUsernameTogetherEmail( username, email ) ) {
            return false;
        }

        String verificationCode = generateVerificationCode( 6 );

        session.setAttribute( "verificationCode", verificationCode );
        mailService.sendMailForVerificationCode( email, verificationCode );

        // 验证码有效期
        final Timer timer = new Timer( );
        timer.schedule( new TimerTask( ) {
            @Override
            public void run( ) {
                session.removeAttribute( "verificationCode" );
                System.out.println( "checkCode删除成功" );
                timer.cancel( );
            }
        }, 10 * 60 * 1000 ); // 10 minutes

        return true;
    }

    // 检查用户名和邮箱的一致性
    public boolean ifUsernameTogetherEmail( String username, String email ) {
        String storedEmail = userDao.getEmailByUsername( username );

        if ( storedEmail.equals( email ) ) {
            return true;
        } else {
            return false;
        }
    }

    // generate VerificationCode
    public String generateVerificationCode( int length ) {
        Random random = new Random( );
        StringBuilder stringBuilder = new StringBuilder( );

        for ( int i = 0; i < length; i++ ) {
            stringBuilder.append( random.nextInt( 10 ) );
        }

        System.out.println( "code " + stringBuilder.toString() );
        return stringBuilder.toString( );
    }

    public boolean ifOpSuccess( int result ) {

        if ( 0 == result ) {
            return false;
        } else {
            return true;
        }
    }


}
