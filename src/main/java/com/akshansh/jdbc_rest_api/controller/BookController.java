package com.akshansh.jdbc_rest_api.controller;

import com.akshansh.jdbc_rest_api.model.Book;
import com.akshansh.jdbc_rest_api.service.AuthorService;
import com.akshansh.jdbc_rest_api.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
BookController (/api/books):

GET /api/books — List all books
GET /api/books/{id} — Get one book
POST /api/books — Create a new book
PUT /api/books/{id} — Update a book
DELETE /api/books/{id} — Delete a book
GET /api/books/author/{authorId} — Get all books by a specific author
GET /api/books/available — Get all available books
GET /api/books/{id}/details — Get book with author name (JOIN query)
 */

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book created = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        Book updated = bookService.updateBook(book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getAllBooksByAuthorId(@PathVariable int authorId){
        return bookService.getBooksByAuthorId(authorId);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks(){
        return bookService.getAvailableBooks();
    }

    /*
    @GetMapping("/{id}/details")
    public ResponseEntity<Book> getBookWithAuthorDetails(@PathVariable int id){
        return bookService.getBookWithAuthorDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
     */
}
