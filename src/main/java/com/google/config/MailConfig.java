package com.google.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
public class MailConfig {
    @Bean
    public JavaMailSenderImpl mailSender(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("2249006799@qq.com");
        javaMailSender.setPassword("ccyajxsotffsecbh");


        return javaMailSender;
    }
}
