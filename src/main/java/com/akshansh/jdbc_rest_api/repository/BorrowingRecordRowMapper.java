package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowingRecordRowMapper implements RowMapper<BorrowingRecord> {
    @Override
    public BorrowingRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(rs.getInt("id"));
        record.setBookId(rs.getInt("book_id"));
        record.setBorrowerName(rs.getString("borrower_name"));
        record.setBorrowerDate(rs.getDate("borrower_date").toLocalDate());
        record.setReturnDate(rs.getDate("return_date").toLocalDate());
        return record;
    }
}
