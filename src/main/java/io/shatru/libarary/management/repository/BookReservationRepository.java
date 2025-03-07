package io.shatru.libarary.management.repository;

import io.shatru.libarary.management.entity.BookReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservationEntity, Long> {
    List<BookReservationEntity> findByUserId(Long userId);
}
