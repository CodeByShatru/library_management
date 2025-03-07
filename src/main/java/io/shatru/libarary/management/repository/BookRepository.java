package io.shatru.libarary.management.repository;

import io.shatru.libarary.management.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContainingOrAuthorContainingOrIsbn(String title, String author, String isbn);

    Page<BookEntity> findAll(Pageable pageable);
}