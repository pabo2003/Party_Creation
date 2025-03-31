package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send-mail")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send")
    public String sendEmail(@RequestBody MailDTO mailDTO){

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(mailDTO.getSubject());
            message.setTo(mailDTO.getToMail());
            message.setFrom("sandunikhitigama@gmail.com"); // replace with your own email
            message.setText(mailDTO.getMessage());

            javaMailSender.send(message);
            return "Email sent successfully";
        }catch (Exception e){
            return "Failed to send email: " + e.getMessage();
        }

    }
}
