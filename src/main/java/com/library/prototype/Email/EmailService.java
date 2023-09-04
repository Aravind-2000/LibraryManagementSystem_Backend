package com.library.prototype.Email;


import com.library.prototype.Entity.BooksBorrowed;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {


    private JavaMailSender mailSender;


    public void sendMail(String to, BooksBorrowed bookInfo){
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to.trim()));
            mimeMessage.setFrom(new InternetAddress("varavind2000@gmail.com"));
            mimeMessage.setSubject("Regarding the Due of a Borrowed Book");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            try{
                String body = "The Book which you have borrowed '"+bookInfo.getBorrowedBook().getBookName()+"' has passed its due date (i.e) "
                        + bookInfo.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+" " +
                        ", SO kindly return the book to the library or fine will be followed.";
                bodyPart.setText(body);
                multipart.addBodyPart(bodyPart);
            }
            catch (Exception e){
                log.info(e.getMessage());
            }
            mimeMessage.setContent(multipart);
        };
        try{
            mailSender.send(preparator);
            log.info("Mail Sent successfully");
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
    }

    public void sendMail_2(String to, BooksBorrowed bookInfo) throws MessagingException, UnsupportedEncodingException {
        String subject = "Regarding the Due of a Borrowed Book";
        String senderName = "Library Management Admin";
        String emailContent = "The Book which you have borrowed '"+bookInfo.getBorrowedBook().getBookName()+"' has passed its due date (i.e) "
                + bookInfo.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+" " +
                ", SO kindly return the book to the library or fine will be followed.";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("varavind2000@gmail.com", senderName);
        messageHelper.setTo(to.trim());
        messageHelper.setSubject(subject);
        messageHelper.setText(emailContent, false);
        try{
            mailSender.send(message);
            log.info("EMAIL SENT SUCCESSFULLY");
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
    }


}
