package io.shatru.libarary.management.service;

import io.shatru.libarary.management.dto.BookRequest;
import io.shatru.libarary.management.entity.BookEntity;
import io.shatru.libarary.management.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> searchBooks(String title, String author, String isbn) {
        return bookRepository.findByTitleContainingOrAuthorContainingOrIsbn(title, author, isbn);
    }

    public Page<BookEntity> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public BookEntity createBook(BookRequest bookRequest) {
        BookEntity bookEntity = mapRequestToEntity(bookRequest);
        return bookRepository.save(bookEntity);
    }

    private BookEntity mapRequestToEntity(BookRequest bookRequest) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequest.title());
        bookEntity.setAuthor(bookRequest.author());
        bookEntity.setPublicationYear(bookRequest.publicationYear());
        bookEntity.setIsbn(bookRequest.isbn());
        bookEntity.setLanguage(bookRequest.language());
        bookEntity.setCreatedAt(LocalDateTime.now());
        return  bookEntity;
    }
}
