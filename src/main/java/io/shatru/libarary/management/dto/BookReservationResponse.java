package io.shatru.libarary.management.dto;

import java.time.LocalDateTime;

public record BookReservationResponse(Long id, Long userId, Long bookId, LocalDateTime reservationDate, LocalDateTime dueDate, String status, String contactMethod, String notes) {}
