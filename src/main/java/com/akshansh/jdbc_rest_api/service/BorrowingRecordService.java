package com.akshansh.jdbc_rest_api.service;

import com.akshansh.jdbc_rest_api.model.Book;
import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import com.akshansh.jdbc_rest_api.repository.BookRepository;
import com.akshansh.jdbc_rest_api.repository.BorrowingRecordRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository repo;
    private final BookRepository booksRepo;

    public BorrowingRecordService(BorrowingRecordRepository repo, BookRepository booksRepo){
        this.repo = repo;
        this.booksRepo = booksRepo;
    }

    @Transactional
    public void borrowBook(int bookId, String borrowerName, Date borrowDate) throws IllegalStateException{Book book = booksRepo.findById(bookId);

        if(book.getAvailableCopies() <= 0){
            throw new IllegalStateException("No available copies for this book");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        booksRepo.update(book);

        BorrowingRecord record = new BorrowingRecord(null, bookId, borrowerName, borrowDate, null);
        repo.save(record);
    }

    @Transactional
    public void returnBook(Integer recordId, Date returnDate){
        BorrowingRecord record = repo.findById(recordId);

        // Update available_copies and save book
        Book book = booksRepo.findById(record.getBookId());
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        booksRepo.save(book);

        // Update and save record
        repo.markAsReturned(recordId, returnDate);
    }

    public List<BorrowingRecord> getAllRecords(){
        return repo.findAll();
    }

    public Optional<BorrowingRecord> getRecordById(int id){
        try{
            BorrowingRecord record = repo.findById(id);
            return Optional.of(record);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public List<BorrowingRecord> getAllActiveRecords(){
        return repo.findActiveRecords();
    }

    public List<BorrowingRecord> getRecordsByBorrowerName(String name){
        return repo.findByBorrowerName(name);
    }
}
