package io.shatru.libarary.management.controller;

import io.shatru.libarary.management.dto.BookReservationResponse;
import io.shatru.libarary.management.entity.BookReservationEntity;
import io.shatru.libarary.management.service.BookReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookReservationResponse>> getReservationsByUserId(@PathVariable Long userId) {
        List<BookReservationResponse> reservations = bookReservationService.getReservationsByUserId(userId)
                .stream()
                .map(reservation -> new BookReservationResponse(
                        reservation.getId(),
                        reservation.getUser().getId(),
                        reservation.getBook().getId(),
                        reservation.getReservationDate(),
                        reservation.getDueDate(),
                        reservation.getStatus(),
                        reservation.getPreferredContactMethod(),
                        reservation.getNotes()))
                .toList();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/create")
    public ResponseEntity<BookReservationResponse> createReservation(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam String contactMethod, @RequestParam String notes) {
        BookReservationEntity reservation = bookReservationService.createReservation(userId, bookId, contactMethod, notes);
        BookReservationResponse response = new BookReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getBook().getId(),
                reservation.getReservationDate(),
                reservation.getDueDate(),
                reservation.getStatus(),
                reservation.getPreferredContactMethod(),
                reservation.getNotes());
        return ResponseEntity.ok(response);
    }
}
