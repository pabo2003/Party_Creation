package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.MailDTO;
import lk.ijse.party_creation.dto.MailDTO1;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/send-mail")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/send")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestBody MailDTO1 mailDTO1) {
        ResponseDTO responseDTO = new ResponseDTO();
        System.out.println(mailDTO1);

        String subject = "Dear "+mailDTO1.getName()+" , \n\nThank you for contacting Party Creation. We have received your message.\nWe will get back to you shortly.\n\nBest regards,\nParty Creation Team";

        try {
            // Create and send email logic
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(subject);
            message.setTo(mailDTO1.getMail());
            message.setFrom("sandunikhitigama@gmail.com");
            message.setText("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");

            javaMailSender.send(message);

            responseDTO.setCode(200);
            responseDTO.setMessage("Email sent successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            responseDTO.setCode(500);
            responseDTO.setMessage("Failed to send email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
