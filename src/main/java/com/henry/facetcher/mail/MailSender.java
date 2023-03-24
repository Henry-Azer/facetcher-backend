package com.henry.facetcher.mail;

import com.henry.facetcher.dto.base.notification.NotificationDto;

/**
 * @author Henry Azer
 * @since 23/02/2023
 */
public interface MailSender {
    void sendEmail(NotificationDto notificationDto);
}
