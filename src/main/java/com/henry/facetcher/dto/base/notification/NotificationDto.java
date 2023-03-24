package com.henry.facetcher.dto.base.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private String toEmail;
    private String subject;
    private String body;
    private String templateName;
    private Map<String, Object> templateModel;
}
