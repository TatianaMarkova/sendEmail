package com.example.email;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class SimpleEmailExampleController {



    @Autowired
     JavaMailSender emailSender;


    @GetMapping(value = MyConstants.SEND_HTML_EMAIL)
     String sendSimpleEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(MyConstants.FRIEND_EMAIL);

        helper.setSubject("Test send HTML email");


        this.emailSender.send(message);

        return "Email Sent!";
    }

}