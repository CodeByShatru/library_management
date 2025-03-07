package io.shatru.libarary.management.controller;

import io.shatru.libarary.management.dto.BookRequest;
import io.shatru.libarary.management.dto.BookResponse;
import io.shatru.libarary.management.entity.BookEntity;
import io.shatru.libarary.management.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest){
      var response =   bookService.createBook(bookRequest);
      return ResponseEntity.status(200).body(new BookResponse(response.getId(),response.getTitle(), response.getAuthor(), response.getPublicationYear(), response.getIsbn(), response.getLanguage(), response.isAvailability()));
    }
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(@RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        List<BookResponse> books = bookService.searchBooks(title, author, isbn)
                .stream()
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn(), book.getLanguage(), book.isAvailability()))
                .toList();
        return ResponseEntity.ok(books);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(Pageable pageable) {
        Page<BookResponse> books = bookService.getAllBooks(pageable)
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn(), book.getLanguage(), book.isAvailability()));
        return ResponseEntity.ok(books);
    }
}
