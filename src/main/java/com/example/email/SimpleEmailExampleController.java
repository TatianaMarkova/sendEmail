package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


@RestController
public class SimpleEmailExampleController {

    @Autowired
     JavaMailSender emailSender;


    @GetMapping(value = MyConstants.SEND)
     String sendSimpleEmail() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email from Java");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }


}