package io.shatru.libarary.management.service;

import io.shatru.libarary.management.entity.BookEntity;
import io.shatru.libarary.management.entity.BookReservationEntity;
import io.shatru.libarary.management.entity.UserEntity;
import io.shatru.libarary.management.repository.BookRepository;
import io.shatru.libarary.management.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookReservationService {
    private final BookReservationRepository bookReservationRepository;
    private final BookRepository bookRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository, BookRepository bookRepository) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookReservationEntity> getReservationsByUserId(Long userId) {
        return bookReservationRepository.findByUserId(userId);
    }

    public BookReservationEntity createReservation(Long userId, Long bookId, String contactMethod, String notes) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Book"+ book);
        if (!book.isAvailability()) {
            throw new RuntimeException("Book is not available");
        }
        book.setAvailability(false);
        bookRepository.save(book);

        BookReservationEntity reservation = new BookReservationEntity();
        reservation.setUser(new UserEntity());
        reservation.setBook(book);
        reservation.setPreferredContactMethod(contactMethod);
        reservation.setNotes(notes);
        reservation.setDueDate(LocalDateTime.now().plusDays(14));
        return bookReservationRepository.save(reservation);
    }
}
