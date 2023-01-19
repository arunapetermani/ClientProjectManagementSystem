package com.example.demo.client.email;

import com.example.demo.client.dto.ClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSenderService {
    @Autowired
    JavaMailSender mailSender;
    SimpleMailMessage message;
    Logger logger = LoggerFactory.getLogger(MailSenderService.class);
    public String sendMailForRegistration(ClientDetails clientDetails) {
        logger.info("Inside of sendMail method");
        message = new SimpleMailMessage();
        message.setFrom("arunamsc.m@gmail.com");
        logger.info("Recipiant mail address"+clientDetails.getClientEmail());
        message.setTo(clientDetails.getClientEmail());
        message.setSubject("Registration");
        message.setText("This Email for adding New Project  ");
        mailSender.send(message);
        logger.info("After sent the message");
        return "Mail sent successfully";
    }
    public String sendMailForCancellation(ClientDetails clientDetails) {
        System.out.println("Inside of sendMail cancel method");
        message = new SimpleMailMessage();
        message.setFrom("arunamsc.m@gmail.com");
        //message.setTo("r.kingstone@gmail.com");
        message.setSubject("Cancellation");
        message.setText("This Email for cancelled Project");
        mailSender.send(message);
        System.out.println("After sent the message");
        return "Mail sent successfully";
    }

}