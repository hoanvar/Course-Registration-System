package com.noti.notificationservice.service;

import com.noti.notificationservice.dto.MessageDTO;
import com.noti.notificationservice.repository.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDTO messageDTO) {
        log.info(messageDTO.toString());
        emailService.sendEmail(messageDTO);
    }
}
