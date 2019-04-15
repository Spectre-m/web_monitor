package com.zjgsu.service;

import com.zjgsu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Spect on 2018/11/27.
 */
@Service
public class MailService extends InitMail {

    // 发送邮件
    public void sendMail( String email, ArrayList< String > resultList ) {
        StringBuilder stringBuilder = new StringBuilder( );

        for ( String result : resultList ) {
            stringBuilder.append( result );
            stringBuilder.append( "\n" );
        }

        String mailText = stringBuilder.toString( );
        System.out.println( email );

        InitMail mail = new InitMail( );
        // 发送普通文本
        // ("smtp.qq.com","706548532@qq.com","123456");
        mail.setInitData( "smtp.163.com", "13362169839@163.com", "web123456" );
        mail.simpleMailSend( email, "小E提醒", mailText );

    }

    // 验证码邮件
    public void sendMailForVerificationCode( String email, String verificationCode ) {
        String mailText = "VerificationCode\n" + verificationCode;
        InitMail mail = new InitMail( );
        // 发送普通文本
        // ("smtp.qq.com","706548532@qq.com","123456");
        mail.setInitData( "smtp.163.com", "13362169839@163.com", "web123456" );
        mail.simpleMailSend( email, "小E提醒", mailText );
    }

}

class InitMail {
    // Spring的邮件工具类，实现了MailSender和JavaMailSender接口
    private JavaMailSenderImpl mailSender;

    /**
     * 初始化邮件发送数据
     *
     * @param host     服务器
     * @param username 发送人
     * @param password 发送人密码
     */
    protected void setInitData( String host, String username, String password ) {
        System.out.println( "初始化邮件......" );
        //创建邮件发送服务器
        mailSender = new JavaMailSenderImpl( );
        mailSender.setHost( host );
        // mailSender.setPort(465);
        mailSender.setUsername( username );
        mailSender.setPassword( password );
        //加认证机制
        Properties javaMailProperties = new Properties( );
        javaMailProperties.put( "mail.smtp.auth", true );
        javaMailProperties.put( "mail.smtp.starttls.enable", true );
        javaMailProperties.put( "mail.smtp.timeout", 5000 );
        mailSender.setJavaMailProperties( javaMailProperties );
        System.out.println( "初始化邮件发送信息完成" );
    }

    /**
     * 发送普通文本
     *
     * @param email   对方邮箱地址
     * @param subject 主题
     * @param text    邮件内容
     */
    protected void simpleMailSend( String email, String subject, String text ) {
        System.out.println( "发送邮件......" );
        System.out.println( "email " + email );
        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage( );
        message.setFrom( mailSender.getUsername( ) );
        message.setTo( email );
        message.setSubject( subject );
        message.setText( text );
        //发送邮件
        mailSender.send( message );
        System.out.println( "发送成功" );
    }
}


//    /**
//     * 发送附件,支持多附件
//     * //使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
//     //MimeMessages为复杂邮件模板，支持文本、附件、html、图片等。
//     * @param email 对方邮箱
//     * @param subject 主题
//     * @param text 内容
//     * @param paths 附件路径，和文件名
//     * @throws MessagingException
//     */
//    public void attachedSend(String email,String subject,String text,Map<String,String> paths) throws MessagingException {
//
//        MimeMessage message = mailSender.createMimeMessage();
//        //创建MimeMessageHelper对象，处理MimeMessage的辅助类
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        //使用辅助类MimeMessage设定参数
//        helper.setFrom(mailSender.getUsername());
//        helper.setTo(email);
//        helper.setSubject(subject);
//        helper.setText(text);
//
//        if (paths!=null){
//            paths.forEach((k,v)->{
//                //加载文件资源，作为附件
//                FileSystemResource file = new FileSystemResource(v);
//                try {
//                    //添加附件
//                    helper.addAttachment(k, file);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        //发送邮件
//        mailSender.send(message);
//        System.out.println("发送成功");
//    }
//
//    /**
//     * 发送html文件，支持多图片
//     * @param email 对方邮箱
//     * @param subject 主题
//     * @param text 内容
//     * @param paths 富文本中添加用到的路径，一般是图片，或者css,js文件
//     * @throws MessagingException
//     */
//    public void richContentSend(String email,String subject,String text,Map<String,String> paths) throws MessagingException {
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setFrom(mailSender.getUsername());
//        helper.setTo(email);
//        helper.setSubject(subject);
//        //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，
//        //需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
//        helper.setText(text,true);
//        //文件地址相对应src目录
//        // ClassPathResource file = new ClassPathResource("logo.png");
//
//        if (paths!=null){
//            paths.forEach((k,v)->{
//                //文件地址对应系统目录
//                FileSystemResource file = new FileSystemResource(v);
//                try {
//                    helper.addInline(k, file);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        mailSender.send(message);
//        System.out.println("发送成功");
//    }
//
//    /**
//     * 群发多人，且多附件
//     * @param mailto 多人邮件地址
//     * @param subject 主题
//     * @param text 内容
//     * @param filePath 文件路径
//     * @throws Exception
//     */
//    public void sendBatchMailWithFile(String[] emails, String subject, String text,  String[] filePath) throws Exception {
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        messageHelper.setFrom(new InternetAddress(MimeUtility.encodeText(mailSender.getUsername())));
//        messageHelper.setSubject(subject);
//        if (filePath != null) {
//            BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
//            mdp.setContent(text, "text/html;charset=UTF-8");// 给BodyPart对象设置内容和格式/编码方式
//            Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对象
//            mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
//            // 把mm作为消息对象的内容
//            MimeBodyPart filePart;
//            FileDataSource filedatasource;
//            // 逐个加入附件
//            for (int j = 0; j < filePath.length; j++) {
//                filePart = new MimeBodyPart();
//                filedatasource = new FileDataSource(filePath[j]);
//                filePart.setDataHandler(new DataHandler(filedatasource));
//                try {
//                    filePart.setFileName(MimeUtility.encodeText(filedatasource.getName()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mm.addBodyPart(filePart);
//            }
//            mimeMessage.setContent(mm);
//        } else {
//            messageHelper.setText(text, true);
//        }
//
//        List<InternetAddress> list = new ArrayList<InternetAddress>();// 不能使用string类型的类型，这样只能发送一个收件人
//        for (int i = 0; i < emails.length; i++) {
//            list.add(new InternetAddress(emails[i]));
//        }
//        InternetAddress[] address = list.toArray(new InternetAddress[list.size()]);
//
//        mimeMessage.setRecipients(Message.RecipientType.TO, address);
//        mimeMessage = messageHelper.getMimeMessage();
//
//        mailSender.send(mimeMessage);
//        System.out.println("发送成功");
//    }


/*    // 发送邮件
    public static void main( String args[] ) throws Exception {
        MailService mail = new MailService( );
        //测试发送普通文本
        //  test.setInitData("smtp.qq.com","706548532@qq.com","123456");
        mail.setInitData( "smtp.163.com", "13362169839@163.com", "web123456" );
        mail.simpleMailSend( "1606500855@qq.com", "小Y提醒", "内地散蒂萨\nmdlksadm克里斯蒂可拉斯基的。" );

        //测试发送附件
       *//* test.setInitData("smtp.163.com","1234@163.com","1234");
        Map<String,String> map = new HashMap<String, String>();
        map.put("test12.xls", "D:\\tomcat8\\apache-tomcat-8.0.29\\test12.xls");
        map.put("wsdl.rar", "D:\\wsdl.rar");
        test.attachedSend("706548532@qq.com","Hello Attachment","This is a mail with attachment",map);
       *//*

        //测试发送富文本（html文件）
      *//*  test.setInitData("smtp.163.com","1234@163.com","1234");
        String text = "<body><p style='color:red;'>Hello Html Email</p><img src='cid:file'/></body>";
        Map<String,String> map = new HashMap<String, String>();
        map.put("file", "E:\\1f7827.jpg");
        test.richContentSend("706548532@qq.com","邮件标题",text,map);*//*

        //测试群发多人多附件
        *//*test.setInitData( "smtp.163.com", "1234@163.com", "1234" );
        String[] address = { "706548532@qq.com", "1326624701@qq.com" };
        String[] filePath = { "D:\\tomcat8\\apache-tomcat-8.0.29\\test12.xls", "D:\\wsdl.rar" };
        test.sendBatchMailWithFile( address, "群发多文件", "实时", filePath );*//*
    }*/

