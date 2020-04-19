package dev.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
