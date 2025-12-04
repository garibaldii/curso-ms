package com.gft.book_service.repository;

import com.gft.book_service.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
