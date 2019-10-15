package com.atguigu.test;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.atguigu.utils.DesUtil;

public class SpringJavaMailTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-*.xml");
		JavaMailSenderImpl javaMail = (JavaMailSenderImpl) context.getBean("sendMail");
		javaMail.setDefaultEncoding("UTF-8");
		MimeMessage mail = javaMail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setSubject("Hello");
		StringBuilder content = new StringBuilder();
		String param = "hello world";
		param = DesUtil.encrypt(param, "abcdefghijklmnopqrstuvwxyz");
		
		content.append("<a href='http://www.atcrowdfunding.com/test/act.do?p="+ param + "'>º§ªÓ¡¥Ω”</a>");
		
		helper.setText(content.toString(), true);
		helper.setFrom("admin@atguigu.com");
		helper.setTo("test@atguigu.com");
		javaMail.send(mail);
	}
}
