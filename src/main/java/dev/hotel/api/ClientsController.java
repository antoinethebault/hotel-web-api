package dev.hotel.api;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

	@Autowired
	ClientRepository clientRepository;

	// GET : clients?start=x&size=y ----- Pagination
	@GetMapping
	public List<Client> clients(@RequestParam Optional<Integer> start, @RequestParam Optional<Integer> size) {
		List<Client> clients;

		if (start.isPresent() && size.isPresent()) {
			Pageable page = PageRequest.of(start.get(), size.get());
			Page<Client> pageClients = clientRepository.findAll(page);
			clients = pageClients.toList();
		} else {
			clients = clientRepository.findAll();
		}

		return clients;
	}

	// GET : clients/uuid ------- Recuperation d'un client
	@GetMapping("{uuid}")
	public ResponseEntity<Object> clients(@PathVariable Optional<String> uuid) {
		if (uuid.isPresent()) {
			String s = uuid.get().replace("-", "");
			UUID uuid2 = new UUID(new BigInteger(s.substring(0, 16), 16).longValue(),
					new BigInteger(s.substring(16), 16).longValue());
			Optional<Client> client = clientRepository.findByUuid(uuid2);
			if (client.isPresent()) {
				return ResponseEntity.status(200).body(client.get());
			} else
				return ResponseEntity.status(404).body("Erreur : le client n'a pas ete retrouve");
		} else
			return ResponseEntity.status(400).body("Erreur : l'uuid doit etre renseigne");
	}

	// POST : clients/ ------ creation d'un client
	@PostMapping
	public ResponseEntity<Object> clients(@RequestBody Client client) {
		if (client.getNom().length() < 2 || client.getPrenoms().length() < 2) {
			return ResponseEntity.status(400).body("Erreur : nom et prenoms doivent avoir au moins deux caracteres");
		} else {
			clientRepository.save(client);
			return ResponseEntity.status(200).body(client);
		}
	}

}
