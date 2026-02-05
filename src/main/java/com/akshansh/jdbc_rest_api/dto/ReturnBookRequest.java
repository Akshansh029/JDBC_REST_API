package com.akshansh.jdbc_rest_api.dto;

import java.util.Date;

public class ReturnBookRequest {
    private Date returnDate;

    public ReturnBookRequest(){}

    public ReturnBookRequest(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
