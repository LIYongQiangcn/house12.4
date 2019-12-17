package com.example.common;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

//    Logger logger = LoggerFactory.getLogger(this.getClass());   private JavaMailSender mailSender;

    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        System.out.println("发送者是："+from);

        message.setTo(to);
        System.out.println("发送到："+to);

        message.setSubject(title);
        System.out.println("标题是："+title);

        message.setText(content);
        System.out.println("邮件内容是："+content);
        mailSender.send(message);
        System.out.println("结果是 :"+message);
        System.out.println("service邮件发送成功2");
//        logger.info("邮件发送成功");
    }

}
