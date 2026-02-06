package com.akshansh.jdbc_rest_api.controller;

import com.akshansh.jdbc_rest_api.dto.BorrowBookRequest;
import com.akshansh.jdbc_rest_api.dto.ReturnBookRequest;
import com.akshansh.jdbc_rest_api.exceptions.ResourceNotFoundException;
import com.akshansh.jdbc_rest_api.exceptions.ValidationException;
import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import com.akshansh.jdbc_rest_api.service.BorrowingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/borrowings")
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
                .orElseThrow(() -> new ResourceNotFoundException("Not found borrowing record with id: " + id));
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowBookRequest request){
        if(request == null || request.getBorrowerName().isEmpty() || request.getBorrowDate() == null){
            throw new ValidationException("Required fields of author are not valid");
        }
        try{
            recordService.borrowBook(
                request.getBookId(),
                request.getBorrowerName(),
                request.getBorrowDate()
            );
            return ResponseEntity.ok("Book borrowed");
        } catch(IllegalStateException e){
            throw new ResourceNotFoundException("No available copies");
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable int id, @RequestBody ReturnBookRequest request){
        Optional<BorrowingRecord> record = recordService.getRecordById(id);

        if(record.isEmpty()){
            throw new ResourceNotFoundException("No record with given id");
        } else{
            if(record.get().getReturnDate() != null){
                throw new ValidationException("Book already returned");
            }
            recordService.returnBook(id, request.getReturnDate());
            return ResponseEntity.ok().body("Book returned successfully");
        }
    }

    @GetMapping("/active")
    public List<BorrowingRecord> getAllActiveRecords(){
        return recordService.getAllActiveRecords();
    }

    @GetMapping("/borrower/{name}")
    public List<BorrowingRecord> getRecordsByBorrowerName(@PathVariable String name){
        if(name.isEmpty()){
            throw new ValidationException("Name cannot be empty");
        }
        return recordService.getRecordsByBorrowerName(name);
    }
}
