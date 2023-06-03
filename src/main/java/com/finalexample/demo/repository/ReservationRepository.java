package com.finalexample.demo.repository;

import com.finalexample.demo.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query(value = "select * from reservations " +
            "where listing_id=:listingId " +
            "and start_date<=:endDateB " +
            "and end_date>=:startDateB", nativeQuery = true)
    Optional<Reservation> findOverlappingReservation(Long listingId, LocalDate startDateB, LocalDate endDateB);

    Page<Reservation> findAllByUsername(String username, Pageable pageable);
}
