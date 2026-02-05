package com.akshansh.jdbc_rest_api.model;
import java.util.Date;

public class BorrowingRecord {
    private Integer id;
    private Integer bookId;
    private String borrowerName;
    private Date borrowerDate;
    private Date returnDate;

    public BorrowingRecord(){}

    public BorrowingRecord(Integer id, Integer bookId, String borrowerName, Date borrowerDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.borrowerDate = borrowerDate;
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Date getBorrowerDate() {
        return borrowerDate;
    }

    public void setBorrowerDate(Date borrowerDate) {
        this.borrowerDate = borrowerDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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
