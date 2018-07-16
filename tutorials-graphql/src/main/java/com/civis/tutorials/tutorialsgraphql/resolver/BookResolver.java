package com.civis.tutorials.tutorialsgraphql.resolver;

import com.civis.tutorials.tutorialsgraphql.model.Author;
import com.civis.tutorials.tutorialsgraphql.model.Book;
import com.civis.tutorials.tutorialsgraphql.repository.AuthorRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;

public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId()).get();
    }
}