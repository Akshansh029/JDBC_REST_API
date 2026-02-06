package com.akshansh.jdbc_rest_api.controller;

import com.akshansh.jdbc_rest_api.exceptions.ResourceNotFoundException;
import com.akshansh.jdbc_rest_api.exceptions.ValidationException;
import com.akshansh.jdbc_rest_api.model.Author;
import com.akshansh.jdbc_rest_api.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
GET /api/authors — List all authors
GET /api/authors/{id} — Get one author
POST /api/authors — Create a new author
PUT /api/authors/{id} — Update an author
DELETE /api/authors/{id} — Delete an author
GET /api/authors/country/{country} — Find authors by country
 */

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id){
        return authorService.getAuthorById(id)
                // if author is present, return ResponseEntity.ok(author)   (HTTP OK 200)
                .map(ResponseEntity::ok)
                // if author is not present, return ResourceNotFoundException   (HTTP 404 NOT FOUND)
                .orElseThrow(() -> new ResourceNotFoundException("Not found author with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        if(author == null || author.getName().isEmpty() || author.getCountry().isEmpty() || author.getBirthYear() <= 0){
            throw new ValidationException("Required fields of author are not valid");
        }
        Author created = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author){
        if(author == null || author.getName().isEmpty() || author.getCountry().isEmpty() || author.getBirthYear() <= 0){
            throw new ValidationException("Required fields of author are not valid");
        }
        author.setId(id);
        Author updated = authorService.updateAuthor(author);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id){
        if(authorService.getAuthorById(id).isEmpty()){
            throw new ResourceNotFoundException("No author with given id to delete");
        }
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/country/{country}")
    public List<Author> getAuthorsByCountry(@PathVariable String country){
        if(country.isEmpty()){
            throw new ValidationException("Country field cannot be empty");
        }
        return authorService.getAuthorsByCountry(country);
    }
}
