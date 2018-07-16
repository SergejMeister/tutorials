package com.civis.tutorials.tutorialsgraphql.repository;

import com.civis.tutorials.tutorialsgraphql.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
