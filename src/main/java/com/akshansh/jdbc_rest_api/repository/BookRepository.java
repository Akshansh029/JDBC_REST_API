package com.akshansh.jdbc_rest_api.repository;

import com.akshansh.jdbc_rest_api.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbc;

    public BookRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public int save(Book book){
        String sql = "INSERT INTO books (title, isbn, author_id, published_year, available_copies)" +
                " VALUES (?,?,?,?,?)";
        return jdbc.update(sql,
                    book.getTitle(),
                    book.getIsbn(),
                    book.getAuthorId(),
                    book.getPublishedYear(),
                    book.getAvailableCopies()
                );
    }

    public List<Book> findAll(){
        String sql = "SELECT * FROM books";
        return jdbc.query(sql, new BookRowMapper());
    }

    public Book findById(int id){
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbc.queryForObject(sql, new BookRowMapper(), id );
    }

    public int update(Book book){
        String sql = "UPDATE books SET title = ?, isbn = ?, author_id = ?, published_year = ?," +
                " available_copies = ? WHERE id = ?";
        return jdbc.update(sql,
                book.getTitle(),
                book.getIsbn(),
                book.getAuthorId(),
                book.getPublishedYear(),
                book.getAvailableCopies(),
                book.getId()
        );
    }

    public int deleteById(int id){
        String sql = "DELETE FROM books WHERE id = ?";
        return jdbc.update(sql, id);
    }

    public List<Book> findByAuthorId(int authorId){
        String sql = "SELECT * FROM books WHERE author_id = ?";
        return jdbc.query(sql, new BookRowMapper(), authorId);
    }

    public List<Book> findAvailableBooks(){
        String sql = "SELECT * FROM books WHERE available_copies > 0";
        return jdbc.query(sql, new BookRowMapper());
    }

    /*
    public Optional<Book> findBookWithAuthorName(int id){
        String sql = "SELECT " +
                "    books.id," +
                "    books.title," +
                "    books.isbn," +
                "    books.published_year," +
                "    books.available_copies," +
                "    authors.name AS author_name" +
                "FROM books" +
                "INNER JOIN authors ON books.author_id = authors.id" +
                "WHERE books.id = ?";

        return jdbc.queryForObject(sql, new BookRowMapper(), id);
    }
     */
}
