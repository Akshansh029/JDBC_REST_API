package com.akshansh.jdbc_rest_api.service;

import com.akshansh.jdbc_rest_api.model.Book;
import com.akshansh.jdbc_rest_api.repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo){
        this.repo = repo;
    }

    @Transactional
    public Book createBook(Book book){
        repo.save(book);
        return book;
    }

    public List<Book> getAllBooks(){
        return repo.findAll();
    }

    public Optional<Book> getBookById(int id){
        try{
            Book book = repo.findById(id);
            return Optional.of(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Book updateBook(Book book){
        repo.update(book);
        return book;
    }

    @Transactional
    public void deleteBookById(int id){
        repo.deleteById(id);
    }

    public List<Book> getBooksByAuthorId(int authorId){
        return repo.findByAuthorId(authorId);
    }

    public List<Book> getAvailableBooks(){
        return repo.findAvailableBooks();
    }

    /*
    public List<Book> getBookWithAuthorDetails(int bookId){
        repo.findBooksWithAuthorName(bookId);
    }
     */
}
