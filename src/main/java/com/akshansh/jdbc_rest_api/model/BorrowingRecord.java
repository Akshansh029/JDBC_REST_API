package com.akshansh.jdbc_rest_api.model;

import java.time.LocalDate;
import java.util.Date;

public class BorrowingRecord {
    private int id;
    private int bookId;
    private String borrowerName;
    private LocalDate borrowerDate;
    private LocalDate returnDate;

    public BorrowingRecord(){}

    public BorrowingRecord(int id, int bookId, String borrowerName, LocalDate borrowerDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.borrowerDate = borrowerDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public LocalDate getBorrowerDate() {
        return borrowerDate;
    }

    public void setBorrowerDate(LocalDate borrowerDate) {
        this.borrowerDate = borrowerDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerDate=" + borrowerDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
