package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
	Optional<Chambre> findByUuid(UUID uuid);
}
