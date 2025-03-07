package io.shatru.libarary.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        @Min(1000)
        @Column(name = "publication_year")
        private int publicationYear;

        @NotBlank
        private String isbn;

        @NotBlank
        private String language;

        private boolean availability = true;

        @Column(name="created_at")
        private LocalDateTime createdAt;

        @Column(name="updated_at")
        private LocalDateTime updatedAt;
    }

