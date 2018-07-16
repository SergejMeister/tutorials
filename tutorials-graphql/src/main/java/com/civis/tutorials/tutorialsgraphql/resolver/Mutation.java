package com.civis.tutorials.tutorialsgraphql.resolver;

import com.civis.tutorials.tutorialsgraphql.model.Author;
import com.civis.tutorials.tutorialsgraphql.model.Book;
import com.civis.tutorials.tutorialsgraphql.repository.AuthorRepository;
import com.civis.tutorials.tutorialsgraphql.repository.BookRepository;
import com.civis.tutorials.tutorialsgraphql.resolver.exceptions.BookNotFoundException;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return authorRepository.save(author);
    }

    public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public Book updateBookPageCount(Integer pageCount, Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()) {
            throw new BookNotFoundException("The book to be updated was not found", id);
        }
        Book book = bookOptional.get();
        book.setPageCount(pageCount);

        return bookRepository.save(book);
    }
}
