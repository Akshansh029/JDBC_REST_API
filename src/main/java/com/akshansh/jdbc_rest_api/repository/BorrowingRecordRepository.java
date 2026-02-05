package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.Book;
import com.akshansh.jdbc_rest_api.model.BorrowingRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BorrowingRecordRepository {
    private final JdbcTemplate jdbc;

    public BorrowingRecordRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public int save(BorrowingRecord record){
        String sql = "INSERT INTO borrowing_records (book_id, borrower_name, borrower_date, return_date)" +
                " VALUES (?,?,?,?)";
        return jdbc.update(sql,
                record.getBookId(),
                record.getBorrowerName(),
                record.getBorrowerDate(),
                record.getReturnDate()
        );
    }

    public List<BorrowingRecord> findAll(){
        String sql = "SELECT * FROM borrowing_records";
        return jdbc.query(sql, new BorrowingRecordRowMapper());
    }

    public BorrowingRecord findById(int id){
        String sql = "SELECT * FROM borrowing_records WHERE id = ?";
        return jdbc.queryForObject(sql, new BorrowingRecordRowMapper(), id );
    }

    public int markAsReturned(int id, LocalDate returnDate){
        String sql = "UPDATE borrowing_records SET return_date = ? WHERE id = ?";
        return jdbc.update(sql, returnDate, id);
    }

    public List<BorrowingRecord> findActiveRecords(){
        String sql = "SELECT * FROM borrowing_records WHERE return_date IS NULL";
        return jdbc.query(sql, new BorrowingRecordRowMapper());
    }

    public List<BorrowingRecord> findByBorrowerName(String name){
        String sql = "SELECT * FROM borrowing_records WHERE borrower_name = ?";
        return jdbc.query(sql, new BorrowingRecordRowMapper(), name);
    }
}
