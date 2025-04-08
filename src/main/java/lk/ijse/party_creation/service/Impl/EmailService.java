package lk.ijse.party_creation.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String code) {
        try{
            System.out.println("Sending verification email to: " + email);
            System.out.println("Main Sending");
            System.out.println("code verify "+code);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("sandunikhitigama@gmail.com");
            message.setSubject("Your Email Verification Code");
            message.setText("Your verification code is: " + code);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
