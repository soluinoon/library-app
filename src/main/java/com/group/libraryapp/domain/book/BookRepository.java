package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Id> {

    Optional<Book> findByName(String name);
}
