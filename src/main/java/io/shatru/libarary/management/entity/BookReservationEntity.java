package io.shatru.libarary.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    private LocalDateTime reservationDate = LocalDateTime.now();

    private LocalDateTime dueDate;

    private String status = "ACTIVE";

    private String preferredContactMethod;

    private String notes;
}
