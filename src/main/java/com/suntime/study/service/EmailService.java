package com.suntime.study.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String to, String verificationLink) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Email Verification");
            helper.setText("이메일 인증을 완료하려면 다음 링크를 클릭하세요.: <a href=\"http://localhost:8080/verify-email?email=" + to + "&token=" + verificationLink + "\">이메일 인증하기</a>", true);

            // 서버 아이피
//            helper.setText("이메일 인증을 완료하려면 다음 링크를 클릭하세요.: <a href=\"http://35.216.17.70:8080/verify-email?email=" + to + "&token=" + verificationLink + "\">이메일 인증하기</a>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
