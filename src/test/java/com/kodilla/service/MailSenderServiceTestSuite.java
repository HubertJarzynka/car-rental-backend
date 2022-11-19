package com.kodilla.service;

import com.kodilla.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@ExtendWith(MockitoExtension.class)
public class MailSenderServiceTestSuite {


    @InjectMocks
    private MailSenderService mailSenderService;
    @Mock
    private JavaMailSender javaMailSender;
    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("janusz.kowalski@gmail.com", "TEST", "TEST");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //When
        mailSenderService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
