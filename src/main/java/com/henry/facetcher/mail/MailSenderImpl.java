package com.henry.facetcher.mail;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.henry.facetcher.dto.base.notification.NotificationDto;
import com.henry.facetcher.service.ConfigValueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static com.henry.facetcher.constants.FacetcherConstants.*;

/**
 * @author Henry Azer
 * @since 23/02/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class MailSenderImpl implements MailSender {
    private final ConfigValueService configValueService;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void sendEmail(NotificationDto notificationDto) {
        log.info("MailSender: sendEmail() - called: " + notificationDto.getToEmail());
        amazonSimpleEmailService.sendEmail(buildSendEmailRequest(notificationDto));
        log.info("MailSender: sendEmail() - ended: " + notificationDto.getToEmail());
    }

    private SendEmailRequest buildSendEmailRequest(NotificationDto notificationDto) {
        try {
            return new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(notificationDto.getToEmail()))
                    .withMessage(new Message().withSubject(new Content().withCharset(configValueService.findConfigValueByConfigKey(CHARSET)).withData(notificationDto.getSubject()))
                    .withBody(new Body().withHtml(new Content().withCharset(configValueService.findConfigValueByConfigKey(CHARSET)).withData(buildHTMLBody(notificationDto)))))
                    .withSource(configValueService.findConfigValueByConfigKey(FROM_MAIL_PERSONAL));
        } catch (Exception e) {
            log.error("MailSender: sendEmail() - exception: " + e.getMessage());
            throw new IllegalArgumentException("Failed to send email to " + notificationDto.getToEmail());
        }
    }

    private String buildHTMLBody(NotificationDto notificationDto) {
        Context thymeleafContext = new Context();
        if (notificationDto.getTemplateModel() != null)
            thymeleafContext.setVariables(notificationDto.getTemplateModel());
        return thymeleafTemplateEngine.process(notificationDto.getTemplateName(), thymeleafContext);
    }
}
