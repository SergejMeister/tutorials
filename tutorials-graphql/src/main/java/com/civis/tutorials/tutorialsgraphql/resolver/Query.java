package com.civis.tutorials.tutorialsgraphql.resolver;

import com.civis.tutorials.tutorialsgraphql.model.Author;
import com.civis.tutorials.tutorialsgraphql.model.Book;
import com.civis.tutorials.tutorialsgraphql.repository.AuthorRepository;
import com.civis.tutorials.tutorialsgraphql.repository.BookRepository;
import com.civis.tutorials.tutorialsgraphql.resolver.exceptions.BookNotFoundException;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.Optional;

public class Query implements GraphQLQueryResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("invalidBookId", id);
        }

        return bookOptional.get();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
}
