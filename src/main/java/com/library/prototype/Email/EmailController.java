package com.library.prototype.Email;

import com.library.prototype.Entity.BooksBorrowed;
import com.library.prototype.Repository.BorrowedBooksRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final BorrowedBooksRepository booksBorrowedService;

    @GetMapping("/notification")
    public void sendNotificationEmail() throws MessagingException, UnsupportedEncodingException {
        BooksBorrowed booksBorrowed = booksBorrowedService.findById(1L).
                    orElseThrow(() -> new RuntimeException("NO BORROWED BOOK FOUND IN THIS ID"));
        emailService.sendMail_2(booksBorrowed.getUser().trim(), booksBorrowed);
    }


}
