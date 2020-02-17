package com.atguigu.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("miku_english@outlook.com");
        msg.setSubject("今晚打游戏");
        msg.setText("打崩坏三");
        javaMailSender.send(msg);
    }

    @Test
    void contextLoads2() throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo("miku_english@outlook.com");
        helper.setSubject("今晚打游戏");
        helper.setText("<b style='color:red'>打崩坏三</b>", true);
        helper.addAttachment("1.jpg", new File("D:\\Download\\image\\1.jpg"));
        helper.addAttachment("2.jpg", new File("D:\\Download\\image\\2.jpg"));
        javaMailSender.send(mimeMessage);
    }

}
