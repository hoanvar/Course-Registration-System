package com.noti.notificationservice.service;

import com.noti.notificationservice.dto.MessageDTO;
import com.noti.notificationservice.repository.EmailService;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("Sending email......");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            
            // Set email properties
            helper.setFrom(from);
            helper.setTo(messageDTO.getEmail());
            helper.setSubject("Course Registration Confirmation");
            
            // Create congratulatory message
            String content = String.format("Chúc mừng %s đã đăng ký khóa học %s thành công!", 
                messageDTO.getStudent_name(),
                messageDTO.getCourse_name());
            helper.setText(content, true);

            // Send email
            javaMailSender.send(message);
            logger.info("Email sent successfully to: " + messageDTO.getEmail());
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage());
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }
}