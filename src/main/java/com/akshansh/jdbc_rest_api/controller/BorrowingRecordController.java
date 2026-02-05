package com.akshansh.jdbc_rest_api.controller;

import com.akshansh.jdbc_rest_api.dto.BorrowBookRequest;
import com.akshansh.jdbc_rest_api.dto.ReturnBookRequest;
import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import com.akshansh.jdbc_rest_api.service.BorrowingRecordService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
BorrowingController (/api/borrowings):

GET /api/borrowings — List all borrowing records
GET /api/borrowings/{id} — Get one record
POST /api/borrowings/borrow — Borrow a book (body: {bookId, borrowerName, borrowDate})
PUT /api/borrowings/{id}/return — Return a book (body: {returnDate})
GET /api/borrowings/active — Get all active (unreturned) borrowings
GET /api/borrowings/borrower/{name} — Get all records for a specific borrower
*/

@RestController
public class BorrowingRecordController {
    private final BorrowingRecordService recordService;

    public BorrowingRecordController(BorrowingRecordService recordService){
        this.recordService = recordService;
    }

    @GetMapping
    public List<BorrowingRecord> getAllRecords(){
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getRecordById(@PathVariable int id){
        return recordService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/borrow")
    public ResponseEntity<Void> borrowBook(@RequestBody BorrowBookRequest request){
        try{
            recordService.borrowBook(
                request.getBookId(),
                request.getBorrowerName(),
                request.getBorrowDate()
            );
            return ResponseEntity.ok().build();
        } catch(IllegalStateException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable int id, @RequestBody ReturnBookRequest request){
        recordService.returnBook(id, request.getReturnDate());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active")
    public List<BorrowingRecord> getAllActiveRecords(){
        return recordService.getAllActiveRecords();
    }

    @GetMapping("/borrower/{name}")
    public List<BorrowingRecord> getRecordsByBorrowerName(@PathVariable String name){
        return recordService.getRecordsByBorrowerName(name);
    }
}
