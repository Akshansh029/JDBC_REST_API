package com.akshansh.jdbc_rest_api.dto;

import java.util.Date;

public class BorrowBookRequest {
    private int bookId;
    private String borrowerName;
    private Date borrowDate;

    public BorrowBookRequest(){}

    public BorrowBookRequest(int bookId, String borrowerName, Date borrowDate) {
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
}
