package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setPublishedYear(rs.getInt("published_year"));
        book.setAvailableCopies(rs.getInt("available_copies"));
        return book;
    }
}
