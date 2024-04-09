package org.godvoyage.godvoyage.service;


import jakarta.mail.internet.MimeMessage;

public interface MailService {
    //랜덤번호 생성
    void createNumber();
    //메일 생성
    MimeMessage CreateMail(String email);
    //메일 발송
    int sendMail(String email);
}
