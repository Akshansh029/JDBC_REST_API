package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {
    private final JdbcTemplate jdbc;

    public AuthorRepository(JdbcTemplate jdbc){     // Constructor Injection
        this.jdbc = jdbc;
    }

    public int save(Author a){
        String sql = "INSERT INTO authors (name, country, birth_year) VALUES(?,?,?)";
        return jdbc.update(sql, a.getName(), a.getCountry(), a.getBirthYear());
    }

    public List<Author> findAll(){
        String sql = "SELECT * FROM authors";
        return jdbc.query(sql, new AuthorRowMapper());
    }

    public Author findById(int id){
        String sql = "SELECT * FROM authors WHERE id = ?";
        return jdbc.queryForObject(sql, new AuthorRowMapper(), id);
    }

    public int update(Author author){
        String sql = "UPDATE authors SET name = ?, country = ?, birth_year = ? WHERE id = ?";
        return jdbc.update(sql,
                author.getName(),
                author.getCountry(),
                author.getBirthYear(),
                author.getId()
        );
    }

    public int deleteById(int id){
        String sql = "DELETE FROM authors WHERE id = ?";
        return jdbc.update(sql, id);
    }

    public List<Author> findByCountry(String country){
        String sql = "SELECT * FROM authors WHERE country = ?";
        return jdbc.query(sql, new AuthorRowMapper(), country);
    }
}
