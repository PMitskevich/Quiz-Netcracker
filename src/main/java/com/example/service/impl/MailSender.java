package com.example.service.impl;
import com.example.exception.InvalidEmailException;
import com.example.exception.NonExistingMailException;
import com.example.exception.detail.ErrorInfo;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    @Value("${spring.mail.username}")
    private String username;

    public MailSender(JavaMailSender mailSender, MessageSource messageSource, CustomValidator customValidator) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
    }


    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (!customValidator.validateByRegexp(emailTo, "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            throw new InvalidEmailException(ErrorInfo.INVALID_EMAIL_ERROR,
                    messageSource.getMessage("message.InvalidEmail", null, LocaleContextHolder.getLocale()));
        }

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        //Добавить проверку на спам

        try {
            mailSender.send(mailMessage);
        }
        catch (MailSendException exception) {
            throw new NonExistingMailException(ErrorInfo.NON_EXISTING_MAIL_ERROR,
                    messageSource.getMessage("message.NonExistingMailError", null, LocaleContextHolder.getLocale()));
        }
    }
}