package com.taskmanager.taskmanager.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("siyamuddin177@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
