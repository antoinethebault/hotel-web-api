package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByUuid(UUID uuid);

	Optional<Client> findByNom(String string);
}
