package com.akshansh.jdbc_rest_api.service;

import com.akshansh.jdbc_rest_api.model.Book;
import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import com.akshansh.jdbc_rest_api.repository.BookRepository;
import com.akshansh.jdbc_rest_api.repository.BorrowingRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository repo;
    private final BookRepository booksRepo;

    public BorrowingRecordService(BorrowingRecordRepository repo, BookRepository booksRepo){
        this.repo = repo;
        this.booksRepo = booksRepo;
    }

    @Transactional
    public int borrowBook(int bookId, String borrowerName, Date borrowDate) throws IllegalStateException{
        Book book = booksRepo.findById(bookId);

        if(book.getAvailableCopies() <= 0){
            throw new IllegalStateException("No available copies for this book");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        booksRepo.update(book);

        BorrowingRecord record = new BorrowingRecord(null, bookId, borrowerName, borrowDate, null);
        return repo.save(record);
    }


}
