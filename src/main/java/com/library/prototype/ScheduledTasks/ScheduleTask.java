package com.library.prototype.ScheduledTasks;

import com.library.prototype.Email.EmailService;
import com.library.prototype.Entity.BookStatus;
import com.library.prototype.Entity.BooksBorrowed;
import com.library.prototype.Repository.BorrowedBooksRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ScheduleTask {


    private BorrowedBooksRepository booksRepository;
    private EmailService emailService;


    @Scheduled(cron = "0 30 10 * * ?") // Executes every day at 10.30 A.M if the due date is surpassed.
    public void invokeTask() throws MessagingException, UnsupportedEncodingException {
        List<BooksBorrowed> booksBorrowed = booksRepository.getAllValidBooks();
        for (BooksBorrowed borrowed : booksBorrowed) {
            if((borrowed.getBookStatus().equals(BookStatus.BORROWED)) &&
                    (LocalDate.now().equals(borrowed.getDueDate()) || LocalDate.now().isAfter(borrowed.getDueDate()))){
                emailService.sendMail_2(borrowed.getUser(), borrowed);
                log.info("Scheduler worked successfully");
            }
        }
    }



}
