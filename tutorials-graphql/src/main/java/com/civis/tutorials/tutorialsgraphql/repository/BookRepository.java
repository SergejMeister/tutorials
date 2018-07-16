package com.civis.tutorials.tutorialsgraphql.repository;

import com.civis.tutorials.tutorialsgraphql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
