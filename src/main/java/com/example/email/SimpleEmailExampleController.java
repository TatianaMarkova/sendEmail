package com.example.email;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
public class SimpleEmailExampleController {

    @Autowired
     JavaMailSender emailSender;


    @GetMapping(value = MyConstants.SEND_WITH_ATTACHMENT)
     String sendSimpleEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Test email with attachments");

        helper.setText("Hello, Im testing email with attachments!");

        String path1 = "/Users/smilyk/Desktop/a/list.txt";
        String path2 = "/Users/smilyk/Desktop/a/list.txt.zip";

        // Attachment 1
        FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("List_id file", file1);

        // Attachment 2
        FileSystemResource file2 = new FileSystemResource(new File(path2));
        helper.addAttachment("Jar", file2);

        emailSender.send(message);

        return "Email Sent!";
    }



}