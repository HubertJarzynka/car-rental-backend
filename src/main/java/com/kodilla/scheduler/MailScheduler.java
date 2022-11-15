package com.kodilla.scheduler;

import com.kodilla.config.AdminConfig;
import com.kodilla.domain.Mail;
import com.kodilla.service.CreateDailyMailMessageService;
import com.kodilla.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailScheduler {
    private static final String SUBJECT = "Car rental: Your daily email!";

    private AdminConfig adminConfig;
    private MailSenderService mailSenderService;
    private CreateDailyMailMessageService createDailyMailMessageService;


    //@Scheduled(cron = "*/30 * * * * *")
    @Scheduled(cron = "0 0 6 * * *")
    public void sendDailyEmail() {
        mailSenderService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                createDailyMailMessageService.emailBodyCreate()));
    }

}