package com.library.prototype.ScheduledTasks;

import com.library.prototype.Email.EmailService;
import com.library.prototype.Entity.BookStatus;
import com.library.prototype.Entity.BooksBorrowed;
import com.library.prototype.Repository.BorrowedBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduleTask {


    private BorrowedBooksRepository booksRepository;
    private EmailService emailService;


    @Scheduled(cron = "0 30 10 * * ?") // Executes every day at 10.30 A.M if the due date is surpassed.
    public void invokeTask(){
        List<BooksBorrowed> booksBorrowed = booksRepository.getAllValidBooks();
        LocalDate currentDate = LocalDate.now();
        for (BooksBorrowed borrowed : booksBorrowed) {
            if(borrowed.getBookStatus().equals(BookStatus.BORROWED)){
                if (currentDate.equals(borrowed.getDueDate()) || currentDate.isAfter(borrowed.getDueDate())) {
                    emailService.sendMail(borrowed.getUser(), borrowed);
                }
            }
        }
    }



}
