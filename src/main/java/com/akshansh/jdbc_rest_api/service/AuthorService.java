package com.akshansh.jdbc_rest_api.service;

import com.akshansh.jdbc_rest_api.model.Author;
import com.akshansh.jdbc_rest_api.repository.AuthorRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo){
        this.repo = repo;
    }

    @Transactional
    public Author createAuthor(Author a){
        repo.save(a);
        return a;
    }

    public List<Author> getAllAuthors(){
        return repo.findAll();
    }

    public Optional<Author> getAuthorById(int id){
        try{
            Author a = repo.findById(id);
            return Optional.of(a);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Transactional
    public Author updateAuthor(Author a){
        repo.update(a);
        return a;
    }

    @Transactional
    public void deleteAuthorById(int id){
        repo.deleteById(id);
    }

    public List<Author> getAuthorsByCountry(String country){
        return repo.findByCountry(country);
    }
}
