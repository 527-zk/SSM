package com.ssm.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送验证码邮件
 * @author kneesh
 * @date 2021/4/27-14:15
 */
public class sendEmail {
    //发件人
    private static String mailFrom = "2681626312@qq.com";
    //授权码
    private static String passwordFrom = "vaaixureyggqdiac";
    //邮件的服务器域名
    private static String mailHost = "smtp.qq.com";
    private static Transport transport = null;
    private static Session session = null;

    public void close(){
        try {
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendCaptchaEmail(String mailTo,String title,String text) throws Exception {
        getTransport();
        // 4、创建邮件
        Message message = createSimpleMail(session,mailFrom,mailTo,title,text);
        // 5、发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }
    //DCL单例模式构造 Transport对象
    public static void getTransport() throws Exception {
        if (transport==null){
            synchronized (Transport.class){
                if (transport==null){
                    Properties properties = new Properties();
                    properties.setProperty("mail.host",mailHost);
                    properties.setProperty("mail.transport.protocol","smtp");
                    properties.setProperty("mail.smtp.auth","true");

                    // 使用JavaMail发送邮件的5个步骤
                    // 1、创建session
                    session = Session.getInstance(properties);
                    // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
                    session.setDebug(true);
                    // 2、通过session得到transport对象
                    transport = session.getTransport();
                    // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
                    transport.connect(mailHost,mailFrom, passwordFrom);
                }
            }
        }
    }
    public static MimeMessage createSimpleMail(Session session, String mailfrom, String mailTo, String mailTittle,
                                               String mailText) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(mailfrom));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        // 邮件的标题
        message.setSubject(mailTittle);
        // 邮件的文本内容
        message.setContent(mailText, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
