package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("id"));
        author.setName(rs.getString("name"));
        author.setCountry(rs.getString("country"));
        author.setBirthYear(rs.getInt("birth_year"));
        return author;
    }
}
