package com.noti.notificationservice.repository;

import com.noti.notificationservice.dto.MessageDTO;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}
