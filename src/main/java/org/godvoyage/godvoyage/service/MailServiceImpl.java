package org.godvoyage.godvoyage.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "godvoyage1@gmail.com";
    private static int number;
    @Override
    public void createNumber() {
        number = (int)(Math.random() * (90000)) + 100000;
    }

    @Override
    public MimeMessage CreateMail(String email) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public int sendMail(String email) {
        MimeMessage message = CreateMail(email);
        javaMailSender.send(message);

        return number;
    }
}
